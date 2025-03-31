//
//package softuni.exam.config;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Service;
//import softuni.exam.service.CountryService;
//import softuni.exam.service.VolcanoService;
//import softuni.exam.service.VolcanologistService;
//
//import javax.xml.bind.JAXBException;
//import java.io.IOException;
//
//@Service
//public class Init implements CommandLineRunner {
//    private final CountryService countryService;
//    private final VolcanoService volcanoService;
//    private final VolcanologistService volcanologistService;
//
//    public Init(CountryService countryService, VolcanoService volcanoService, VolcanologistService volcanologistService) {
//        this.countryService = countryService;
//        this.volcanoService = volcanoService;
//        this.volcanologistService = volcanologistService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        initializeCountries();
//        initializeVolcanoes();
//        initializeVolcanologists();
//        exportVolcanoes();
//    }
//
//    private void exportVolcanoes() {
//        volcanoService.exportVolcanoes();
//    }
//
//    private void initializeVolcanologists() throws IOException, JAXBException {
//        volcanologistService.importVolcanologists();
//    }
//
//    private void initializeVolcanoes() throws IOException {
//        volcanoService.importVolcanoes();
//    }
//
//    private void initializeCountries() throws IOException {
//        countryService.importCountries();
//    }
//}
//
