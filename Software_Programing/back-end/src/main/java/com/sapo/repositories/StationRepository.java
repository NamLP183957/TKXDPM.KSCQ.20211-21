package com.sapo.repositories;

import com.sapo.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer>, JpaSpecificationExecutor<Station> {
    @Query(value = "SELECT count(id) FROM vehicles WHERE parking_slot_id IN (SELECT id FROM parking_slots WHERE STATION_ID = ?)", nativeQuery = true)
    public Integer getNumBikeInStation(Integer id);
}
