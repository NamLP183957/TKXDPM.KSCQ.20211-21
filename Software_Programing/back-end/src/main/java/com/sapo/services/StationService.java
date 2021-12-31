package com.sapo.services;

import com.sapo.entities.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StationService {
    public List<Station> getListStation();
}
