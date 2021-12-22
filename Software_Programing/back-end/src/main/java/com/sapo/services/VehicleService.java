package com.sapo.services;



import com.sapo.dto.vehicle.VehicleDTOResponse;
import com.sapo.dto.vehicle.VehiclePaginationDTO;
import com.sapo.entities.Vehicle;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
  // Lấy ra danh sách vehicle
  List<VehicleDTOResponse> getListVehicle(String keyword);
  
  //Hàm tìm Vehicle bằng id
  Vehicle findVehicleById(int id);
  
  //Hàm search Vehicle
  VehiclePaginationDTO searchVehicle(int page, int limit, String keyword);
}
