package com.sapo.services;

import org.springframework.stereotype.Service;

@Service
public interface ParkingSlotService {
    public boolean processBikeCode(String bikeCode);
}
