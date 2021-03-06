package com.sapo.dto.vehicle;

import com.sapo.entities.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTOResponse {
  private int id;
  private int type;
  private String licensePlate;
  private int battery;
  private int maxTime;
  private long startTime;
  private long timeRented;
  private String fee;
  private int status;
  private int parkingSlotId;

  public VehicleDTOResponse(Vehicle vehicle) {
    this.id = vehicle.getId();
    this.parkingSlotId = vehicle.getParkingSlotId();
    this.type = vehicle.getType();
    this.licensePlate = vehicle.getLicensePlate();
    this.battery = vehicle.getBattery();
    this.maxTime = vehicle.getMaxTime();
    this.status = vehicle.getStatus();
  }
}
