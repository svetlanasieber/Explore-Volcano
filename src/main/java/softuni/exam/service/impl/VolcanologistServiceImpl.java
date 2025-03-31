package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanologistSeedRootDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class VolcanologistServiceImpl implements VolcanologistService {
    private static final String VOLCANOLOGIST_FILE_PATH = "src/main/resources/files/xml/volcanologists.xml";

    private final VolcanologistRepository volcanologistRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final VolcanoService volcanoService;

    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, VolcanoService volcanoService) {
        this.volcanologistRepository = volcanologistRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.volcanoService = volcanoService;
    }

    @Override
    public boolean areImported() {
        return volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return Files
                .readString(Path.of(VOLCANOLOGIST_FILE_PATH));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(VOLCANOLOGIST_FILE_PATH, VolcanologistSeedRootDto.class)
                .getVolcanologistSeedDtos()
                .stream()
                .filter(volcanologistSeedDto -> {
                    boolean isValid = validationUtil.isValid(volcanologistSeedDto);


                    Volcano volcano = volcanoService.findVolcanoById(volcanologistSeedDto.getExploringVolcano());

                    if (volcano == null) {
                        isValid = false;
                    }

                    Volcanologist volcanologist = volcanologistRepository.findByFirstNameAndLastName(volcanologistSeedDto.getFirstName(),
                            volcanologistSeedDto.getLastName()).orElse(null);
                    if (volcanologist != null) {
                        isValid = false;
                    }

                    sb
                            .append(isValid
                                    ? String.format("Successfully imported volcanologist %s %s",
                                    volcanologistSeedDto.getFirstName(),
                                    volcanologistSeedDto.getLastName())
                                    : "Invalid volcanologist")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(volcanologistSeedDto -> {

                    Volcanologist volcanologist = modelMapper.map(volcanologistSeedDto, Volcanologist.class);

                    Volcano volcano = volcanoService.findVolcanoById(volcanologistSeedDto.getExploringVolcano());
                    volcanoService.addAndSaveAddedVolcano(volcano, volcanologist);

                    volcanologist.setExploringVolcano(volcano);
                    return volcanologist;
                })
                .forEach(volcanologistRepository::save);


        return sb.toString();
    }


}
