package com.sapo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "parking_slots")
public class ParkingSlot extends BaseEntity{
    @Column(name = "station_id")
    private int stationId;

    @Column(name = "type")
    private int type;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "booking_time")
    private long restartTime;

    @Column(name = "status")
    private int status;
}
