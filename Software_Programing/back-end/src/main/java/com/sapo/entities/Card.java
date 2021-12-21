package com.sapo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "cards")
public class Card extends BaseEntity{
    @Column(name = "card_code", length = 50)
    private String cardCode;

    @Column(name = "owner", length = 100)
    private String owner;

    @Column(name = "date_expired", length = 50)
    private long dateExpired;

    @Column(name = "cvv_code", length = 50)
    private String cvvCode;

    @Column(name = "status")
    private int status;
}
