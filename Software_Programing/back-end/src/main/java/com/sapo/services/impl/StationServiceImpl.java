package com.sapo.services.impl;

import com.sapo.common.ConstantVariableCommon;
import com.sapo.dto.station.StationDTO;
import com.sapo.entities.Station;
import com.sapo.repositories.StationRepository;
import com.sapo.services.StationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            List<Object[]> result = stationRepository.getBlankSlotEachType(id);
            LOGGER.info("size: " + result.size());
            Integer type, numOfBlank;
            BigInteger numOfBlankBig;

            if (result != null && !result.isEmpty()) {
                for (Object[] object : result) {
                    type = (Integer) object[0];
                    numOfBlankBig = (BigInteger) object[1];
                    numOfBlank = numOfBlankBig.intValue();

                    switch (type) {
                        case ConstantVariableCommon.SINGLE_BIKE:
                            stationDTO.setNumOfBlankBike(numOfBlank);
                            break;
                        case ConstantVariableCommon.SINGLE_ELECTRIC_BIKE:
                            stationDTO.setNumOfBlankElectricBike(numOfBlank);
                            break;
                        case ConstantVariableCommon.DOUBLE_BIKE:
                            stationDTO.setNumOfBlankTwinBike(numOfBlank);
                            break;
                        case ConstantVariableCommon.DOUBLE_ELECTIC_BIKE:
                            stationDTO.setNumOfBlankElectricTwinBike(numOfBlank);
                            break;
                        default:
                            break;
                    }
                }
            }


            return stationDTO;
        }
    }

    @Override
    public List<Station> getStationBySearchKey(String searchKey) {
        return stationRepository.findStationByName(searchKey);
    }
}
