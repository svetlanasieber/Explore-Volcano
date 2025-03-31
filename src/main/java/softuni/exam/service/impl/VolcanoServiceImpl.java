package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanoSeedDto;
import softuni.exam.models.entity.*;
import softuni.exam.models.entity.enums.VolcanoType;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.CountryService;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VolcanoServiceImpl implements VolcanoService {

    private static final String VOLCANOES_FILE_PATH = "src/main/resources/files/json/volcanoes.json";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    private final VolcanoRepository volcanoRepository;

    private final VolcanologistRepository volcanologistRepository;

    private final CountryService countryService;

    public VolcanoServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, VolcanoRepository volcanoRepository, VolcanologistRepository volcanologistRepository, CountryService countryService) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.volcanoRepository = volcanoRepository;
        this.volcanologistRepository = volcanologistRepository;
        this.countryService = countryService;
    }


    @Override
    public boolean areImported() {
        return volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return Files.readString(Path.of(VOLCANOES_FILE_PATH));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readVolcanoesFileContent(), VolcanoSeedDto[].class))
                .filter(volcanoSeedDto -> {
                    boolean isValid = validationUtil.isValid(volcanoSeedDto);

                    Optional<Volcano> byVolcanoName = volcanoRepository.findByName(volcanoSeedDto.getName());
                    if (byVolcanoName.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                                    ? String.format("Successfully imported volcano %s of type %s"
                                    , volcanoSeedDto.getName()
                                    , volcanoSeedDto.getVolcanoType())
                                    : "Invalid volcano")
                            .append(System.lineSeparator());

                    return isValid;
                })
                        .map(volcanoSeedDto -> {
                        Volcano volcano = modelMapper.map(volcanoSeedDto, Volcano.class);

                        Country country = countryService.getCountryById(volcanoSeedDto.getCountry()).orElse(null);

                        country.getVolcanoes().add(volcano);
                        countryService.saveAddedVolcanoInCountry(country);

                        volcano.setCountry(country);
                        return volcano;
                    })
                    .forEach(volcanoRepository::save);

        return sb.toString();
    }

    @Override
    public Volcano findVolcanoById(Long volcanoId) {
        return volcanoRepository.findById(volcanoId).orElse(null);
    }

    @Override
    public void addAndSaveAddedVolcano(Volcano volcano, Volcanologist volcanologist) {
        volcano.getVolcanologists().add(volcanologist);
        volcanoRepository.save(volcano);

    }

    @Override
    public String exportVolcanoes() {

        StringBuilder build = new StringBuilder();

        Set<Volcano> allVolcanoesByType = volcanoRepository.findByElevationGreaterThanAndActiveIsTrueAndLastEruptionIsNotNullOrderByElevationDesc();

        allVolcanoesByType.forEach(v -> {
            build.append(String.format("Volcano: %s\n" +
                                    "   *Located in: %s\n" +
                                    "   **Elevation: %d\n" +
                                    "   ***Last eruption on: %s",
                            v.getName(),
                            v.getCountry().getName(),
                            v.getElevation(),
                            v.getLastEruption()))
                    .append(System.lineSeparator());

        });

        return build.toString();
    }
}
