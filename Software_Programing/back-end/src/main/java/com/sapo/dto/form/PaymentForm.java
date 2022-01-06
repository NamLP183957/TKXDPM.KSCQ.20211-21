package com.sapo.dto.form;

import com.sapo.entities.Card;
import lombok.Data;

import javax.persistence.Column;

@Data
public class PaymentForm {
//    private String cardCode;
//
//    private String owner;
//
//    private String dateExpired;
//
//    private String cvvCode;
    private Card card;

    private int amount;

    private boolean rentDay;
}
