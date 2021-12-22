package com.sapo.services;

import com.sapo.dto.parkingSlot.ParkingSlotDTO;
import org.springframework.stereotype.Service;

@Service
public interface ParkingSlotService {
    public ParkingSlotDTO processBikeCode(String bikeCode);
}
