package com.sapo.dto.PaymentTransaction;

import com.sapo.entities.Card;

import javax.persistence.Column;

public class PaymentTransactionDTO {
    private String errorCode;
    private Card card;
    private String transactionId;
    private String transactionContent;
    private int amount;
    private String createdAt;

    public PaymentTransactionDTO(String errorCode,Card card, String transactionId, String transactionContent,
                              int amount, String createdAt) {
        super();
        this.errorCode = errorCode;
        this.card = card;
        this.transactionId = transactionId;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public String getErrorCode() {
        return errorCode;
    }
}