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
    private InvoiceService invoiceServuice;
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
    @PostMapping("/deposit/{vehicleId}")
    public ResponseEntity<Object> deposit(@RequestBody PaymentForm paymentForm, @PathVariable("vehicleId")Integer vehicleId) {
        try {
            Card card = paymentForm.getCard();
            boolean isRentDay = paymentForm.isRentDay();

            InterbankInterface interbank = new InterbankSubsystem();
            PaymentTransactionDTO paymentTransaction = interbank.pay(card, paymentForm.getAmount(), "Deposit");

            saveTransaction(paymentTransaction, vehicleId);
            updateVehicleAndParkingSlot(vehicleId, isRentDay);
            return ResponseEntity.ok(paymentTransaction);
        } catch (PaymentException | UnrecognizedException ex) {
            return ResponseEntity.badRequest().body("Can't payment");
        }
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

        invoiceServuice.createInvoice(invoice);
    }

    /**
     * Cập nhật lại trạng thái xe và ô để xe
     * @param vehicleId     - id của xe cập nhật
     * @param isRentDay     - hình thức thuê xe
     */
    private void updateVehicleAndParkingSlot(Integer vehicleId, boolean isRentDay) {
        Vehicle vehicle = vehicleService.findVehicleById(vehicleId);
        Integer parkingSlotId = vehicle.getParkingSlotId();
        ParkingSlot parkingSlot = parkingSlotService.geParkingSlotById(parkingSlotId);
        if (isRentDay) {
            vehicleService.updateVehicleStatusAndParkingSlot(vehicleId, ConstantVariableCommon.RENTED_DAY_VEHICLE_STATUS, 0);
        } else {
            vehicleService.updateVehicleStatusAndParkingSlot(vehicleId, ConstantVariableCommon.RENTED_VEHICLE_STATUS, 0);
        }
        parkingSlotService.updateParkingSLotStatus(parkingSlotId, ConstantVariableCommon.BLANK_SLOT_STATUS);
    }

}
