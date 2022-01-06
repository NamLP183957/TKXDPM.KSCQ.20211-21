package com.sapo.entities;

import com.sapo.common.ConstantVariableCommon;
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

  public long caculateDeposit(){
    switch (type){
      case ConstantVariableCommon.SINGLE_BIKE:
        return 400000;
      case ConstantVariableCommon.DOUBLE_BIKE:
        return 550000;
      case ConstantVariableCommon.DOUBLE_ELECTIC_BIKE:
        return 800000;
      case ConstantVariableCommon.SINGLE_ELECTRIC_BIKE:
        return 700000;
      default:
        return 0;
    }
  }

}
