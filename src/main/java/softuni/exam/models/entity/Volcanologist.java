package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "volcanologists")
public class Volcanologist extends BaseEntity {

    private String firstName;
    private String lastName;

    private int age;

    private Double salary;

    private LocalDate exploringFrom;

    private Volcano exploringVolcano;


    @Column(unique = true, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(unique = true, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    @Column(nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(nullable = false)
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Column
    public LocalDate getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(LocalDate exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    @ManyToOne
    public Volcano getExploringVolcano() {
        return exploringVolcano;
    }

    public Volcanologist setExploringVolcano(Volcano exploringVolcanoes) {
        this.exploringVolcano = exploringVolcanoes;
        return this;
    }
}
