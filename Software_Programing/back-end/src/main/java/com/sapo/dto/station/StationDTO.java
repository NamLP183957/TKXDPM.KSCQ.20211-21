package com.sapo.dto.station;

import com.sapo.entities.Station;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class StationDTO {
    private Integer id;
    private String name;

    private String address;

    private String area;

    private int numOfCurrBike;

    private int numOfBlankBike;

    private int numOfBlankElectricBike;

    private int numOfBlankTwinBike;

    private int numOfBlankElectricTwinBike;

    public StationDTO(Station station) {
        this.id = station.getId();
        this.name = station.getName();
        this.address = station.getAddress();
        this.area = station.getArea();
    }

}
