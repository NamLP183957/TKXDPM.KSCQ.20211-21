package com.sapo.services.impl;

import com.sapo.repositories.ParkingSlotRepository;
import com.sapo.services.ParkingSlotService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParkingSlotServiceImpl implements ParkingSlotService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParkingSlotServiceImpl.class.toString());
    private ParkingSlotRepository parkingSlotRepository;
    @Override
    public boolean processBikeCode(String bikeCode) {
        boolean exists = parkingSlotRepository.existsByCode(bikeCode);
        if (exists) {
            return true;
        } else {
            return false;
        }
    }
}
