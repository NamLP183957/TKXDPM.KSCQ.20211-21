package com.sapo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {
  @Column(name = "parking_slot_id")
  private int parkingSlotId;

  @Column(name = "type")
  private int type;

  @Column(name = "license_plate", length = 50)
  private String licensePlate;

  @Column(name = "battery")
  private int battery;

  @Column(name = "max_time")
  private int maxTime;

  @Column(name = "status")
  private int status;
}
