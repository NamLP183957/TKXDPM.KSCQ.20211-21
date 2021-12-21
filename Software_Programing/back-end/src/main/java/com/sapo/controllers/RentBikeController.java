package com.sapo.controllers;

import com.sapo.services.ParkingSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/rentbike")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
public class RentBikeController {
    private ParkingSlotService parkingSlotService;
    @PostMapping("/process-bike-code")
    public ResponseEntity<Object> processBikeCode(@RequestBody String bikeCode) {
        if (parkingSlotService.processBikeCode(bikeCode)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
