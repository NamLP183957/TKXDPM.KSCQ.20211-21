package com.sapo.dto.form;

import com.sapo.dto.parkingSlot.ParkingSlotDTO;
import com.sapo.entities.Vehicle;
import lombok.Data;

@Data
public class PaymentFormReturnBike extends PaymentForm{
    private Vehicle vehicle;
    private Integer parkingSlotId;
}
