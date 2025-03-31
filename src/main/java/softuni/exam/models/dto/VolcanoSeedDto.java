package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.enums.VolcanoType;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class VolcanoSeedDto {

    @Expose
    private String name;
    @Expose
    private int elevation;
    @Expose
    private VolcanoType volcanoType;
    @Expose
    private boolean isActive;
    @Expose
    private String lastEruption;
    @Expose
    private Long country;

    @Size(min = 2, max = 30)
    public String getName() {
        return name;
    }

    public VolcanoSeedDto setName(String name) {
        this.name = name;
        return this;
    }

    @Positive
//    @Digits(integer = 4, fraction = 0,message = "Value must have at most 4 digits")
    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public VolcanoType getVolcanoType() {
        return volcanoType;
    }

    public VolcanoSeedDto setVolcanoType(VolcanoType volcanoType) {
        this.volcanoType = volcanoType;
        return this;
    }


    public boolean isActive() {
        return isActive;
    }

    public VolcanoSeedDto setActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public String getLastEruption() {

        return lastEruption;
    }

    public VolcanoSeedDto setLastEruption(String lastEruption) {
        this.lastEruption = lastEruption;
        return this;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
