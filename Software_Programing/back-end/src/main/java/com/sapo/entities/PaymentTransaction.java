package com.sapo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
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
}
