package com.sapo.services;

import com.sapo.dto.parkingSlot.ParkingSlotDTO;
import com.sapo.entities.ParkingSlot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingSlotService {
    public ParkingSlotDTO processBikeCode(String bikeCode);

    public List<ParkingSlotDTO> getBlankSlotByType(Integer stationId, Integer type);

    boolean updateParkingSLotStatus(Integer id, Integer status);

    public ParkingSlot geParkingSlotById(Integer id);
}
