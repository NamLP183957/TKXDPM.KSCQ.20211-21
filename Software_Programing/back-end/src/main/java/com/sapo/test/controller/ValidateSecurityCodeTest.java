package com.sapo.test.controller;

import com.sapo.controllers.RentBikeController;
import com.sapo.entities.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateSecurityCodeTest {
    private Card card;

    @BeforeEach
    void setUp() throws Exception {
        card = new Card();
    }

    @ParameterizedTest
    @CsvSource({
            "12, true",
            "524, true",
            "1455@, false"
    })
    public void test(String cvvCode, boolean expected) {
        card.setCvvCode(cvvCode);
        boolean isValided = card.validateSecurityCode();
        assertEquals(expected, isValided);
    }
}
