package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "volcanologists")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistSeedRootDto {

    @XmlElement(name = "volcanologist")
    private List<VolcanologistSeedDto> volcanologistSeedDtos;

    public List<VolcanologistSeedDto> getVolcanologistSeedDtos() {
        return volcanologistSeedDtos;
    }

    public VolcanologistSeedRootDto setVolcanologistSeedDtos(List<VolcanologistSeedDto> volcanologistSeedDtos) {
        this.volcanologistSeedDtos = volcanologistSeedDtos;
        return this;
    }
}
