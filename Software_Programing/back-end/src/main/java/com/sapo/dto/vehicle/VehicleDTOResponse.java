package com.sapo.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VehicleDTOResponse {
  private int id;
  private int parkingSlotId;
  private String type;
  private String licensePlate;
  private String battery;
  private String maxTime;
  private String status;
}
