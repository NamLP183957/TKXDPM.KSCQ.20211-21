package com.sapo.services.impl;

import com.sapo.entities.Station;
import com.sapo.repositories.StationRepository;
import com.sapo.services.StationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StationServiceImpl implements StationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StationServiceImpl.class.toString());
    private StationRepository stationRepository;
    @Override
    public List<Station> getListStation() {
        return stationRepository.findAll();
    }
}
