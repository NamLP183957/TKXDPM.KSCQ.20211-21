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
  private long timeRented;
  private double fee;
  private int status;
}
