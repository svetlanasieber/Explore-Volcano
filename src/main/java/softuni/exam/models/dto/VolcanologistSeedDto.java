package softuni.exam.models.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistSeedDto {

    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "last_name")
    private String lastName;
    @XmlElement(name = "salary")
    private Double salary;
    @XmlElement(name = "age")
    private int age;
    @XmlElement(name = "exploring_from")
    private String exploringFrom;
    @XmlElement(name = "exploring_volcano_id")
    private Long exploringVolcano;

    @NotNull
    @Size(min = 2,max = 30)
    public String getFirstName() {
        return firstName;
    }

    public VolcanologistSeedDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull
    @Size(min = 2,max = 30)
    public String getLastName() {
        return lastName;
    }

    public VolcanologistSeedDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Double getSalary() {
        return salary;
    }

    public VolcanologistSeedDto setSalary(Double salary) {
        this.salary = salary;
        return this;
    }
    @Min(18)
    @Max(80)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(String exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    public Long getExploringVolcano() {
        return exploringVolcano;
    }

    public VolcanologistSeedDto setExploringVolcano(Long exploringVolcano) {
        this.exploringVolcano = exploringVolcano;
        return this;
    }
}
