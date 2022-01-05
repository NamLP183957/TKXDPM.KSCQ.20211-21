package com.sapo.controllers;

import com.sapo.dto.station.StationDTO;
import com.sapo.entities.Station;
import com.sapo.services.StationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStationById(@PathVariable(name = "id")Integer id) {
        StationDTO station = stationService.getStationDetailById(id);
        if (station == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(station);
        }
    }

    @GetMapping("/search/{searchKey}")
    public ResponseEntity<Object> getStationBySearchKey(@PathVariable(name = "searchKey")String searchKey){
        return ResponseEntity.ok(stationService.getStationBySearchKey(searchKey));
    }
}
