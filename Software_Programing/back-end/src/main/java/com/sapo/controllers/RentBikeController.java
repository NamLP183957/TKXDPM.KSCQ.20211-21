package com.sapo.controllers;

import com.sapo.common.ConstantVariableCommon;
import com.sapo.dto.PaymentTransaction.PaymentTransactionDTO;
import com.sapo.dto.form.PaymentForm;
import com.sapo.dto.parkingSlot.ParkingSlotDTO;
import com.sapo.dto.vehicle.VehicleDTOResponse;
import com.sapo.entities.*;
import com.sapo.exception.PaymentException;
import com.sapo.exception.UnrecognizedException;
import com.sapo.services.*;
import com.sapo.subsystem.InterbankInterface;
import com.sapo.subsystem.InterbankSubsystem;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.ConstantCallSite;
import java.util.Date;

@RequestMapping("api/rentbike")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
public class RentBikeController {
    private ParkingSlotService parkingSlotService;
    private VehicleService vehicleService;
    private PaymentTransactionService transactionService;
//    private CardService cardService;
    private InvoiceService invoiceService;
//
//    public RentBikeController(){
//
//    }
    /**
     * Phương thức này dùng để xử lý mã bike code mỗi khi người dùng gửi code lên hệ thống
     * @param bikeCode  - mã code do người dùng nhập lên
     * @return
     */
    @PostMapping("/process-bike-code")
    public ResponseEntity<Object> processBikeCode(@RequestBody String bikeCode) {
        ParkingSlotDTO parkingSlotDTO = parkingSlotService.processBikeCode(bikeCode);
        System.out.println("bikeCode is " + bikeCode);
        if (parkingSlotDTO.getId() == 0) {
            System.out.println("Error code");
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
    @PostMapping("/deposit/{vehicleId}")
    public ResponseEntity<Object> deposit(@RequestBody PaymentForm paymentForm, @PathVariable("vehicleId")Integer vehicleId) {
        try {
            /*if (!validateDateString(paymentForm.getCvvCode())) {
                return ResponseEntity.badRequest().body("Invalid cvv code");
            }
            if (!validateDateString(paymentForm.getDateExpired())) {
                return ResponseEntity.badRequest().body("Invalid date expired");
            }*/
            InterbankInterface interbank = new InterbankSubsystem();
            Card card = paymentForm.getCard();
//            card.setCardCode(paymentForm.getCardCode());
//            card.setCvvCode(paymentForm.getCvvCode());
//            card.setOwner(paymentForm.getOwner());
//            card.setDateExpired(Long.parseLong(paymentForm.getDateExpired()));
            System.out.println("dateExpired: " + card.getDateExpired());
            PaymentTransactionDTO paymentTransaction = interbank.pay(card, paymentForm.getAmount(), "Deposit");

            saveTransaction(paymentTransaction, vehicleId);
            updateVehicleAndParkingSlot(vehicleId);
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
     * Phương thức này dùng để lưu thông tin giao dịch và bắt đầu tạo giao dịch và tạo hóa đơn
     * @param paymentTransaction
     */
    private void saveTransaction(PaymentTransactionDTO paymentTransaction, Integer vehicleId) {
//        Card card = paymentTransaction.getCard();
//        Card c = cardService.saveCard(card);

        PaymentTransaction transaction = new PaymentTransaction();
//        transaction.setCardId(c.getId());
        transaction.setContent(paymentTransaction.getTransactionContent());
        transaction.setAmount(paymentTransaction.getAmount());

        // transaction.setCreatedAt(Long.parseLong(paymentTransaction.getCreatedAt()));
        transaction.setMethod("pay");
        transaction.setErrorCode(paymentTransaction.getErrorCode());
        PaymentTransaction trans = transactionService.saveTransaction(transaction);
        Vehicle vehicle = vehicleService.findVehicleById(vehicleId);

        Invoice invoice = new Invoice();
        invoice.setVehicleId(vehicleId);
        invoice.setTransactionId(trans.getId());
        invoice.setStartTime(System.currentTimeMillis());
        invoice.setStatus(ConstantVariableCommon.NOT_DONE_INVOICE);
        invoice.setRestartTime(System.currentTimeMillis());
        invoice.setTotalRentTime(0);
        invoice.setVehicleType(vehicle.getType());

        invoiceService.createInvoice(invoice);
    }


    private void updateVehicleAndParkingSlot(Integer vehicleId) {
        Vehicle vehicle = vehicleService.findVehicleById(vehicleId);
        Integer parkingSlotId = vehicle.getParkingSlotId();
        ParkingSlot parkingSlot = parkingSlotService.geParkingSlotById(parkingSlotId);

        vehicleService.updateVehicleStatusAndParkingSlot(vehicleId, ConstantVariableCommon.RENTED_VEHICLE_STATUS, 0);
        parkingSlotService.updateParkingSLotStatus(parkingSlotId, ConstantVariableCommon.BLANK_SLOT_STATUS);
    }
     /** Kiểm tra xem mã bảo mật có hợp lệ hay không
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
