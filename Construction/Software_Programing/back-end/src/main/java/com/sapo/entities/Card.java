package com.sapo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
public class Card extends BaseEntity{
    private String cardCode;

    private String owner;

    private String dateExpired;

    private String cvvCode;

    public Card(String cardCode, String owner, int cvvCode, String dateExpired) {
        super();
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = String.valueOf(cvvCode);
        this.dateExpired = dateExpired;
    }

    /**
     * Kiểm tra các trường thông tin bắt buộc đã được nhập hay chưa.
     * @return  - true nếu các trường thông tin bắt buộc đã được nhập, false nêu một trong các trường thông tin chưa được nhập
     */
    public boolean validateCard() {
        if (cardCode == null || cardCode.equals("")) return false;
        if (owner == null || owner.equals("")) return false;
        if (cvvCode == null || owner.equals("")) return false;
        if (dateExpired == null || dateExpired.equals("")) return false;
        return true;
    }

    /**
     * Kiểm tra xem mã bảo mật có hợp lệ hay không
     * @return  true nếu mã bảo mật là một chuỗi gồm 3 chữ số, ngược lại là false.
     */
    public boolean validateSecurityCode() {
        if (this.cvvCode.length() != 3) return false;
        try {
            Integer.parseInt(cvvCode);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Kiểm tra xem chuỗi nhập vào có phải ngày hợp lệ hay không.
     * @return     true nếu chuỗi nhập vào gồm 4 chữ số, 2 chữ só đầu biểu diễn cho tháng, 2 chữ số sau biểu diễn cho ngày.
     */
    public boolean validateDateString() {
        if (this.dateExpired.length() != 4) return false;
        try {
            Long.parseLong(this.dateExpired);
            String monthStr = this.dateExpired.substring(0, 2);
            int month = Integer.parseInt(monthStr);
            if (month > 12 || month < 1) return false;

            String dayStr = this.dateExpired.substring(2, 4);
            int day = Integer.parseInt(dayStr);
            switch (month) {
                case 2:
                    if (day >= 30 || day <= 0) return false;
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (day > 31 || day <= 0) return false;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day > 30 || day <= 0) return false;
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
