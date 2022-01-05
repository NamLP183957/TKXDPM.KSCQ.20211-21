//package com.sapo.test.controller;
//
//import com.sapo.controllers.RentBikeController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//public class ValidateDateStringTest {
//    private RentBikeController rentBikeController;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        //rentBikeController = new RentBikeController();
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "12/12/2000, true",
//            "12/21/2000, false",
//            "29/2/2001, false"
//    })
//    public void test(String strDate) {
//        boolean isValided = rentBikeController.validateDateString("12/12/2000");
//        assertEquals(true, isValided);
//    }
//}
