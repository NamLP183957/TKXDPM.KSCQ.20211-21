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

    /**
     * Phương thức này dùng để lấy ra danh sách bãi xe
     * @return danh sách bã xe
     */
    @GetMapping("/")
    public ResponseEntity<Object> getListStation() {
        List<Station> listStation = stationService.getListStation();
        return ResponseEntity.ok(listStation);
    }

    /**
     * Phương thức này dùng để lấy ra bãi xe theo id
     * @param id    - id của bãi xe
     * @return      - thông tin bãi để xe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getStationById(@PathVariable(name = "id")Integer id) {
        StationDTO station = stationService.getStationDetailById(id);
        if (station == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(station);
        }
    }

    /**
     * Phương thức này dùng để láy ra bãi để xe khi tìm kiếm theo từ khóa
     * @param searchKey     - từ khóa tìm kiếm
     * @return bãi để xe có tên chưa từ khóa tìm kiếm
     */
    @GetMapping("/search/{searchKey}")
    public ResponseEntity<Object> getStationBySearchKey(@PathVariable(name = "searchKey")String searchKey){
        return ResponseEntity.ok(stationService.getStationBySearchKey(searchKey));
    }
}
