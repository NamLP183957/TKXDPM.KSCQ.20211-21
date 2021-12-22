package com.sapo.controllers;

import com.sapo.dto.vehicle.VehicleDTOResponse;
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

  @GetMapping("/list/{stationId}")
  public ResponseEntity<List<Vehicle>> getListVehicleInStation(@PathVariable("stationId") int stationId){
      List<Vehicle> vehicles = vehicleService.getListVehicleInStation(stationId);
      return ResponseEntity.ok(vehicles);
  }

    //API tìm thông tin Vehicle bằng id
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findVehicleById(@PathVariable("id") int id){
        Vehicle vehicle = vehicleService.findVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/list-in-rent-time")
    public ResponseEntity<List<Vehicle>> getListVehicleInRentTime(){
        List<Vehicle> vehicles = vehicleService.getListVehicleInRentTime();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/list-in-rent-time/{id}")
    public ResponseEntity<VehicleDTOResponse> findVehicleInRentTimeById(@PathVariable("id") int id){
        VehicleDTOResponse vehicleDTOResponses = vehicleService.findVehicleInRentTimeById(id);
        return ResponseEntity.ok(vehicleDTOResponses);
    }
}
