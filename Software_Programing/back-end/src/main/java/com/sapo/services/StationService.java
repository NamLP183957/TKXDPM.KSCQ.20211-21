package com.sapo.services;

import com.sapo.dto.station.StationDTO;
import com.sapo.entities.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StationService {
    public List<Station> getListStation();
    public Station getStationById(Integer id);
    public StationDTO getStationDetailById(Integer id);
    public List<Station> getStationBySearchKey(String searchKey);
}
