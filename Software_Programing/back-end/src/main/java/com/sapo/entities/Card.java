package com.sapo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
@Getter
@Setter
//@Table(name = "cards")
@NoArgsConstructor
public class Card extends BaseEntity{
//    @Column(name = "card_code", length = 50)
    private String cardCode;

//    @Column(name = "owner", length = 100)
    private String owner;

//    @Column(name = "date_expired", length = 50)
    private long dateExpired;

//    @Column(name = "cvv_code", length = 50)
    private String cvvCode;
//
//    @Column(name = "status")
//    private int status;

    public Card(String cardCode, String owner, int cvvCode, String dateExpired) {
        super();
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = String.valueOf(cvvCode);
        this.dateExpired = Long.parseLong(dateExpired);
    }
}
