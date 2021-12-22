package com.sapo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "stations")
public class Station extends BaseEntity{
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "area", length = 250)
    private String area;

    @Column(name = "num_of_curr_bike")
    private int numOfCurrBike;

    @Column(name = "num_of_blank_bike")
    private int numOfBlankBike;

    @Column(name = "num_of_blank_electric_bike")
    private int numOfBlankElectricBike;

    @Column(name = "num_of_blank_twin_bike")
    private int numOfBlankTwinBike;

    @Column(name = "num_of_blank_electric_twin_bike")
    private int numOfBlankElectricTwinBike;
}
