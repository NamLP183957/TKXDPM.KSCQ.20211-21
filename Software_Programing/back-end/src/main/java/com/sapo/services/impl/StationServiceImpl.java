package com.sapo.services.impl;

import com.sapo.dto.station.StationDTO;
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

    @Override
    public Station getStationById(Integer id) {
        return stationRepository.findById(id).orElse(null);
    }

    @Override
    public StationDTO getStationDetailById(Integer id) {
        Station station = stationRepository.findById(id).orElse(null);
        if (station == null) {
            return null;
        } else {
            StationDTO stationDTO = new StationDTO(station);
            int numCurrBike = stationRepository.getNumBikeInStation(id);
            stationDTO.setNumOfCurrBike(numCurrBike);
            return stationDTO;
        }
    }
}
