package com.sapo.controllers;

import com.sapo.entities.Station;
import com.sapo.services.StationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/stations")
@AllArgsConstructor
public class ViewStationController {
    private StationService stationService;

    @GetMapping("/")
    public ResponseEntity<Object> getListStation() {
        List<Station> listStation = stationService.getListStation();
        return ResponseEntity.ok(listStation);
    }
}
