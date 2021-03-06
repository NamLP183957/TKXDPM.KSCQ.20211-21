package com.sapo.services;

import com.sapo.dto.vehicle.VehicleDTOResponse;
import com.sapo.entities.Vehicle;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
  // Lấy ra danh sách vehicle
  List<Vehicle> getListVehicleInStation(int stationId);

  //Hàm tìm Vehicle bằng id
  Vehicle findVehicleById(int id);

  List<VehicleDTOResponse> getListVehicleInRentTime();

  VehicleDTOResponse findVehicleInRentTimeById(int id);

  // Tìm xe đang đỗ ở trong parking slot
  VehicleDTOResponse findNotRentVehicleByParkingSlot(int parkingSlotId);

  boolean updateVehicleStatusAndParkingSlot(Integer vehicleId, Integer status, Integer parkingSlotId);
}
