package com.sapo.test.controller;

import com.sapo.controllers.RentBikeController;
import com.sapo.entities.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ValidateDateStringTest {
    private Card card;

    @BeforeEach
    void setUp() throws Exception {
        card = new Card();
    }

    @ParameterizedTest
    @CsvSource({
            "1125, true",
            "302sd, false",
            "2005, false"
    })
    public void test(String strDate, boolean expected) {
        card.setDateExpired(strDate);
        boolean isValided = card.validateDateString();
        assertEquals(expected, isValided);
    }
}
