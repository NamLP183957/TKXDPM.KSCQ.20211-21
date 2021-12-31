package com.sapo.services;

import com.sapo.dto.parkingSlot.ParkingSlotDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingSlotService {
    public ParkingSlotDTO processBikeCode(String bikeCode);

    public List<ParkingSlotDTO> getBlankSlotByType(Integer stationId, Integer type);

    boolean updateParkingSLotService(Integer id, Integer status);
}
