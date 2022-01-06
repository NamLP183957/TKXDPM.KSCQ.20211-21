package com.sapo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "parking_slots")
public class ParkingSlot extends BaseEntity{
    @Column(name = "station_id")
    private int stationId;

    @Column(name = "type")
    private int type;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "booking_time")
    private long restartTime;

    @Column(name = "status")
    private int status;

    /** Kiểm tra xem mã bảo mật có hợp lệ hay không
     * @return  true nếu mã bảo mật là một chuỗi gồm các kí tự là chữ cái hoặc chữ số
     */
    public boolean validateBikeCode() {
        // checks if the String is null
        if (this.code == null){
            return false;
        }
        int len = this.code.length();
        for (int i = 0; i < len; i++) {
            // checks whether the character is neither a letter nor a digit
            // if it is neither a letter nor a digit then it will return false
            if ((Character.isLetterOrDigit(this.code.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
