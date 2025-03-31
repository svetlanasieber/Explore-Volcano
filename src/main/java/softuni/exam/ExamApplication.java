package softuni.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

//    public static class JDKVersionChecker {
//
//        public static void main(String[] args) {
//            String javaVersion = getJavaVersion();
//            System.out.println("Java version: " + javaVersion);
//        }
//
//        public static String getJavaVersion() {
//            String version = System.getProperty("java.version");
//            System.out.println(version);
//            return version;
//        }
//    }

}
