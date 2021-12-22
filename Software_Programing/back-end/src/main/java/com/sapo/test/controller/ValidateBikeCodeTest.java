package com.sapo.test.controller;

import com.sapo.controllers.RentBikeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateBikeCodeTest {
    private RentBikeController rentBikeController;

    @BeforeEach
    void setUp() throws Exception {
        rentBikeController = new RentBikeController();
    }

    @ParameterizedTest
    @CsvSource({
            "123, true",
            "324, true",
            "12313, false"
    })
    public void test(String bikeCode) {
        boolean isValided = rentBikeController.validateBikeCode(bikeCode);
        assertEquals(true, isValided);
    }
}
