package com.sapo.repositories;

import com.sapo.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer>, JpaSpecificationExecutor<Station> {
    @Query(value = "SELECT count(id) FROM vehicles WHERE parking_slot_id IN (SELECT id FROM parking_slots WHERE STATION_ID = ?)", nativeQuery = true)
    public Integer getNumBikeInStation(Integer id);

    @Query(value="SELECT * FROM stations WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    public List<Station> findStationByName(@Param("name") String name);

    @Query(value = "SELECT type, COUNT(id) AS So_Luong FROM parking_slots WHERE (station_id = ?) AND (status = 1) GROUP BY type", nativeQuery = true)
    public List<Object[]> getBlankSlotEachType(Integer stationId);
}
