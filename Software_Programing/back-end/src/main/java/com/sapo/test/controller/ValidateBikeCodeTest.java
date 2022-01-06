package com.sapo.test.controller;

import com.sapo.controllers.RentBikeController;
import com.sapo.entities.Card;
import com.sapo.entities.ParkingSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateBikeCodeTest {
    private ParkingSlot parkingSlot;

    @BeforeEach
    void setUp() throws Exception {
        parkingSlot = new ParkingSlot();
    }

    @ParameterizedTest
    @CsvSource({
            "123, true",
            "324, true",
            "1231&, false"
    })
    public void test(String bikeCode, boolean expected) {
        parkingSlot.setCode(bikeCode);
        boolean isValided = parkingSlot.validateBikeCode();
        assertEquals(expected, isValided);
    }
}
