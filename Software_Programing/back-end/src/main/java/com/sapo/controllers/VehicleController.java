package com.sapo.controllers;

import com.sapo.dto.vehicle.VehicleDTOResponse;
import com.sapo.entities.Vehicle;
import com.sapo.services.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class nay chua cac phuong thuc lấy thông tin xe trong bãi và xe đang thuê
 * @author Dat_BT_20183880
 */
@RequestMapping("/api/vehicles")
@CrossOrigin("http://localhost:3000")
@RestController
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Lấy list xe trong bãi xe được chọn
     * @variable stationId: id của bãi xe
     * @return vehicles: thong tin xe
     */
  @GetMapping("/list/{stationId}")
  public ResponseEntity<List<Vehicle>> getListVehicleInStation(@PathVariable("stationId") int stationId){
      List<Vehicle> vehicles = vehicleService.getListVehicleInStation(stationId);
      return ResponseEntity.ok(vehicles);
  }

    /**
     * Lấy thông tin xe theo id xe
     * @variable id: id của xe
     * @return vehicles: thong tin xe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findVehicleById(@PathVariable("id") int id){
        Vehicle vehicle = vehicleService.findVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    /**
     * Lấy thông tin xe theo id xe
     * @return vehicles: thong tin xe đang thuê
     */
    @GetMapping("/list-in-rent-time")
    public ResponseEntity<List<VehicleDTOResponse>> getListVehicleInRentTime(){
        List<VehicleDTOResponse> vehicles = vehicleService.getListVehicleInRentTime();
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Lấy thông tin xe theo id xe
     * @variable id: id của xe đang thuê
     * @return vehicles: thong tin xe đang thuê
     */
    @GetMapping("/list-in-rent-time/{id}")
    public ResponseEntity<VehicleDTOResponse> findVehicleInRentTimeById(@PathVariable("id") int id){
        VehicleDTOResponse vehicleDTOResponses = vehicleService.findVehicleInRentTimeById(id);
        return ResponseEntity.ok(vehicleDTOResponses);
    }
}
