package com.sapo.repositories;

import com.sapo.entities.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer>, JpaSpecificationExecutor<ParkingSlot> {
    public ParkingSlot findByCode(String code);
}
