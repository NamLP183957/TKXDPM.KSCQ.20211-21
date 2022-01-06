package com.sapo.dto.parkingSlot;

import com.sapo.entities.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
public class ParkingSlotDTO {
    private int id;

    private int stationId;

    private int type;

    private long restartTime;

    private int status;

    public ParkingSlotDTO() {
        this.id = 0;
    }

    public ParkingSlotDTO(ParkingSlot parkingSlot) {
        this.id = parkingSlot.getId();
        this.stationId = parkingSlot.getStationId();
        this.type = parkingSlot.getType();
        this.restartTime = parkingSlot.getRestartTime();
        this.status = parkingSlot.getStatus();
    }
}
