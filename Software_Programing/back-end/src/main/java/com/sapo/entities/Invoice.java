package com.sapo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "invoices")
public class Invoice extends BaseEntity{
    @Column(name = "vehicle_id")
    private int vehicleId;

    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "start_time")
    private long startTime;

    @Column(name = "restart_time")
    private long restartTime;

    @Column(name = "total_rent_time")
    private int totalRentTime;

    @Column(name = "status")
    private int status;
}
