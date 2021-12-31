package com.sapo.repositories;

import com.sapo.entities.ParkingSlot;
import com.sapo.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer>, JpaSpecificationExecutor<ParkingSlot> {
    @Query(value = "select parking_slots.* from parking_slots where parking_slots.code = ?", nativeQuery = true)
    ParkingSlot findByCode(String code);

    @Query(value = "select parking_slots.* from parking_slots where parking_slots.station_id = ?", nativeQuery = true)
    List<ParkingSlot> findByStationId(int stationId);

    List<ParkingSlot> findByStationIdAndTypeAndStatus(Integer staionId, Integer type, Integer status);
}
