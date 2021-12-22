package com.sapo.repositories;

import com.sapo.dto.vehicle.VehicleDTOResponse;
import com.sapo.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>, JpaSpecificationExecutor<Vehicle> {
  @Query(value = "select vehicles.* from vehicles where vehicles.id = ?", nativeQuery = true)
  Vehicle findVehicleById(int id);

  @Query(value = "select vehicles.* from vehicles where vehicles.license_plate = ?", nativeQuery = true)
  Vehicle findVehicleBylicensePlate(String licensePlate);

  @Query(value = "select vehicles.* from vehicles where vehicles.parking_slot_id = ?", nativeQuery = true)
  Vehicle findVehicleByParkingSlotId(int parkingSlotId);

  @Query(value = "select vehicles.* from vehicles where vehicles.status = ?", nativeQuery = true)
  List<Vehicle> findVehicleByStatus(int status);
}
