package com.sapo.entities;

import com.sapo.common.ConstantVariableCommon;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.concurrent.TimeUnit;

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

    @Column(name = "vehicle_type")
    private int vehicleType;

    public long caculateManualRentFee() {
        long currTime = System.currentTimeMillis();
        long totalRentTime = (currTime - restartTime) + this.totalRentTime;
        long totalRentTimeMinus = TimeUnit.MILLISECONDS.toMinutes(totalRentTime);

        long manualFee = 0;
        if (totalRentTimeMinus <= 10) {
            return manualFee = 0;
        } else if (totalRentTimeMinus > 10 && totalRentTime <= 30) {
            manualFee = 10000;
        } else {
            manualFee = 10000 + Math.round(totalRentTimeMinus/15) * 3000;
        }

        long fee = 0;
        switch (vehicleType) {
            case ConstantVariableCommon.SINGLE_BIKE:
                fee = manualFee;
                break;
            case ConstantVariableCommon.SINGLE_ELECTRIC_BIKE:
            case ConstantVariableCommon.DOUBLE_BIKE:
                fee = manualFee * 3 / 2;
                break;
            case ConstantVariableCommon.DOUBLE_ELECTIC_BIKE:
                fee = manualFee * 2;
                break;
            default:
                break;
        }

        return fee;
    }

    public long caculateRentDayFee() {
        long currTime = System.currentTimeMillis();
        long totalRentTime = (currTime - restartTime) + this.totalRentTime;
        long totalRentTimeMinus = TimeUnit.MILLISECONDS.toMinutes(totalRentTime);
        long totalRentTimeHour = TimeUnit.MILLISECONDS.toHours(totalRentTime);

        long staticFee = 200000;
        long manualFee = 0;
        long fee = 0;
        if (totalRentTimeHour < 12) {
             manualFee = staticFee - (12 - totalRentTimeHour) * 10000;
        } else if (totalRentTimeHour >= 12 && totalRentTime <= 24){
            manualFee = staticFee;
        } else {
            manualFee = staticFee + Math.round((totalRentTimeMinus - 24*60) / 15) * 2000;
        }

        switch (vehicleType) {
            case ConstantVariableCommon.SINGLE_BIKE:
                fee = manualFee;
                break;
            case ConstantVariableCommon.SINGLE_ELECTRIC_BIKE:
            case ConstantVariableCommon.DOUBLE_BIKE:
                fee = manualFee * 3 / 2;
                break;
            case ConstantVariableCommon.DOUBLE_ELECTIC_BIKE:
                fee = manualFee * 2;
                break;
            default:
                break;
        }
        return fee;
    }
}
