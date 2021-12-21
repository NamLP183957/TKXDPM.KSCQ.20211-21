package com.sapo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payment_transactions")
public class PaymentTransaction extends BaseEntity{
    @Column(name = "card_id")
    private int cardId;

    @Column(name = "method", length = 100)
    private String method;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "created_at")
    private long createdAt;

    @Column(name="error_code")
    private String errorCode;

    @Column(name="amount")
    private int amount;

    public PaymentTransaction(int cardId, String method, String content, long createdAt, String errorCode, int amount) {
        this.cardId = cardId;
        this.method = method;
        this.content = content;
        this.createdAt = createdAt;
        this.errorCode = errorCode;
        this.amount = amount;
    }
}
