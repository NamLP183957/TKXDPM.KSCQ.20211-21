package com.sapo.controllers;


import com.sapo.dto.vehicle.VehicleDTOResponse;
import com.sapo.dto.vehicle.VehiclePaginationDTO;
import com.sapo.entities.Vehicle;
import com.sapo.services.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/vehicles")
@CrossOrigin("http://localhost:3000")
@RestController
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

  @GetMapping()
  public ResponseEntity<List<VehicleDTOResponse>> getListVehicle(@RequestParam String keyword){
      List<VehicleDTOResponse> vehicleDTOS = vehicleService.getListVehicle(keyword);
      return ResponseEntity.ok(vehicleDTOS);
  }

  // API Tìm tất cả Vehicle và phân trang
  @GetMapping("/list")
  public ResponseEntity<VehiclePaginationDTO> listVehiclePagination(@RequestParam int page, @RequestParam int limit, @RequestParam String keyword){
    VehiclePaginationDTO vehiclePaginationDTO = vehicleService.searchVehicle(page, limit, keyword);
    return ResponseEntity.ok(vehiclePaginationDTO);
  }

//    //API lấy list Vehicle
//    @GetMapping
//    public ResponseEntity<List<Vehicle>> listVehicle(@RequestParam String keyword){
//        List<Vehicle> vehicles = vehicleService.findAllListVehicle(keyword);
//        return ResponseEntity.ok(vehicles);
//    }
  
  //API tìm thông tin Vehicle bằng id
  @GetMapping("/{id}")
  public ResponseEntity<Vehicle> findVehicleById(@PathVariable("id") int id){
    Vehicle vehicle = vehicleService.findVehicleById(id);
    return ResponseEntity.ok(vehicle);
  }
  
}
