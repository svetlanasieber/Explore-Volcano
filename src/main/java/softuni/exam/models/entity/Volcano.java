package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.VolcanoType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "volcanoes")
public class Volcano extends BaseEntity {

    private String name;
    private int elevation;
    private VolcanoType volcanoType;

    private boolean isActive;
    private LocalDate lastEruption;

    private Country country;
    private Set<Volcanologist> volcanologists = new HashSet<>();

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }
    @Column
    @Enumerated(EnumType.STRING)
    public VolcanoType getVolcanoType() {
        return volcanoType;
    }
    public void setVolcanoType(VolcanoType volcanoType) {

        this.volcanoType = volcanoType;
    }
    @Column(name = "is_active", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
    @Column
    public LocalDate getLastEruption() {

        return lastEruption;
    }
    public Volcano setLastEruption(LocalDate lastEruption) {
        this.lastEruption = lastEruption;
        return this;
    }

    @ManyToOne()
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "exploringVolcano", fetch = FetchType.EAGER)
    public Set<Volcanologist> getVolcanologists() {
        return volcanologists;
    }

    public Volcano setVolcanologists(Set<Volcanologist> volcanologists) {
        this.volcanologists = volcanologists;
        return this;
    }
}
