package com.sapo.controllers;

import com.sapo.dto.PaymentTransaction.PaymentTransactionDTO;
import com.sapo.dto.form.PaymentForm;
import com.sapo.dto.parkingSlot.ParkingSlotDTO;
import com.sapo.dto.vehicle.VehicleDTOResponse;
import com.sapo.entities.Card;
import com.sapo.entities.Vehicle;
import com.sapo.exception.PaymentException;
import com.sapo.exception.UnrecognizedException;
import com.sapo.services.ParkingSlotService;
import com.sapo.services.VehicleService;
import com.sapo.subsystem.InterbankInterface;
import com.sapo.subsystem.InterbankSubsystem;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/rentbike")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
public class RentBikeController {
    private ParkingSlotService parkingSlotService;
    private VehicleService vehicleService;

    public RentBikeController(){

    }
    /**
     * Phương thức này dùng để xử lý mã bike code mỗi khi người dùng gửi code lên hệ thống
     * @param bikeCode  - mã code do người dùng nhập lên
     * @return
     */
    @PostMapping("/process-bike-code")
    public ResponseEntity<Object> processBikeCode(@RequestBody String bikeCode) {
        ParkingSlotDTO parkingSlotDTO = parkingSlotService.processBikeCode(bikeCode);

        if (parkingSlotDTO.getId() == 0) {
            return ResponseEntity.badRequest().body("Invalid bike code!");
        } else {
            VehicleDTOResponse vehicleDTO = vehicleService.findNotRentVehicleByParkingSlot(parkingSlotDTO.getId());
            return ResponseEntity.ok(vehicleDTO);
        }
    }

    /**
     * Phương thức này dùng để xử lý yêu cầu khi người dùng muốn đặt cọc để thuê xe
     * @param paymentForm   -chứa các thông tin về thể thanh toán và số tiền cần thanh toán
     * @return
     */
    @PostMapping("/deposit")
    private ResponseEntity<Object> deposit(@RequestBody PaymentForm paymentForm) {
        try {
            if (!validateDateString(paymentForm.getCvvCode())) {
                return ResponseEntity.badRequest().body("Invalid cvv code");
            }
            if (!validateDateString(paymentForm.getDateExpired())) {
                return ResponseEntity.badRequest().body("Invalid date expired");
            }
            InterbankInterface interbank = new InterbankSubsystem();
            Card card = new Card();
            card.setCardCode(paymentForm.getCardCode());
            card.setCvvCode(paymentForm.getCvvCode());
            card.setOwner(paymentForm.getOwner());
            card.setDateExpired(Long.parseLong(paymentForm.getDateExpired()));

            PaymentTransactionDTO paymentTransaction = interbank.pay(card, paymentForm.getAmount(), "Deposit");
            return ResponseEntity.ok(paymentTransaction);
        } catch (PaymentException | UnrecognizedException ex) {
            return ResponseEntity.badRequest().body("Cann't payment");
        }
    }

    /**
     * Kiểm tra xem mã bảo mật có hợp lệ hay không
     * @param cvvCode   - Mã bảo mật cần kiểm tra
     * @return  true nếu mã bảo mật là một chuỗi gồm 3 chữ số, ngược lại là false.
     */
    public boolean validateSecurityCode(String cvvCode) {
        if (cvvCode.length() != 3) return false;
        try {
            Integer.parseInt(cvvCode);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Kiểm tra xem chuỗi nhập vào có phải ngày hợp lệ hay không.
     * @param dateStr   - Chuỗi nhập vào
     * @return     true nếu chuỗi nhập vào gồm 4 chữ số, 2 chữ só đầu biểu diễn cho tháng, 2 chữ số sau biểu diễn cho ngày.
     */
    public boolean validateDateString(String dateStr) {
        if (dateStr.length() != 4) return false;
        try {
            Long.parseLong(dateStr);
            String monthStr = dateStr.substring(0, 2);
            int month = Integer.parseInt(monthStr);
            if (month > 12 || month < 1) return false;

            String dayStr = dateStr.substring(2, 4);
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

    /**
     * Kiểm tra xem mã bảo mật có hợp lệ hay không
     * @param bikeCode   - Mã bảo mật cần kiểm tra
     * @return  true nếu mã bảo mật là một chuỗi gồm 3 chữ số, ngược lại là false.
     */
    public boolean validateBikeCode(String bikeCode) {
        // checks if the String is null
        if (bikeCode == null){
            return false;
        }
        int len = bikeCode.length();
        for (int i = 0; i < len; i++) {
            // checks whether the character is neither a letter nor a digit
            // if it is neither a letter nor a digit then it will return false
            if ((Character.isLetterOrDigit(bikeCode.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
