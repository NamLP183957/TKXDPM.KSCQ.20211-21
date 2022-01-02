package com.sapo.controllers;

import com.sapo.common.ConstantVariableCommon;
import com.sapo.dto.PaymentTransaction.PaymentTransactionDTO;
import com.sapo.dto.form.PaymentForm;
import com.sapo.dto.form.PaymentFormReturnBike;
import com.sapo.entities.*;
import com.sapo.services.InvoiceService;
import com.sapo.services.ParkingSlotService;
import com.sapo.services.PaymentTransactionService;
import com.sapo.services.VehicleService;
import com.sapo.subsystem.InterbankSubsystem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sapo.subsystem.InterbankInterface;
import com.sapo.subsystem.InterbankSubsystem;
import com.sapo.utils.MyMap;


import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.sapo.exception.PaymentException;
import com.sapo.exception.UnrecognizedException;

@RequestMapping("api/return-bike")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
public class ReturnBikeController {
    private static final int RENT_FEE_A_PART_DAY = 200000;
    private VehicleService vehicleService;
    private ParkingSlotService parkingSlotService;
    private InvoiceService invoiceService;
    private PaymentTransactionService paymentTransactionService;
    /**
     * Represent the Interbank subsystem
     */
      private InterbankInterface interbank;

//    /**
//     * Thuc hien thanh toan tien thue xe cho khach hang
//     * @param card: the card nguoi thanh toan
//     * @param amount: so tien can thanh toan
//     * @param contents: noi dung giao dich
//     * @return result: phan hoi giao dich da thuc hien thanh cong hay chua
//     */
//      @PostMapping("/process-transaction")
//      public Map<String, Object> processTransactionReturnBike(@RequestBody Card card, @RequestBody int amount, @RequestBody String contents ){
//          Map<String, Object> result = new MyMap();
//          result.put("RESULT", "PAYMENT FAILED!");
//          try{
//              PaymentTransactionDTO transaction = this.interbank.refund(card, amount, contents);
//              result.put("RESULT", "PAYMENT SUCCESSFUL!");
//              result.put("MESSAGE", "You have successfully paid!");
//          }catch (PaymentException | UnrecognizedException ex){
//                result.put("MESSAGE", ex.getMessage());
//          }
//
//          return result;
//      }

    @PostMapping("/process-transaction")
    public ResponseEntity<Object> payment(@RequestBody PaymentFormReturnBike paymentFormReturnBike){
        try{
            Vehicle vehicle = paymentFormReturnBike.getVehicle();
            Card card = paymentFormReturnBike.getCard();
            Integer parkingSlotId = paymentFormReturnBike.getParkingSlotId();

            long deposit = vehicle.caculateDeposit();
            Invoice invoice = invoiceService.findNotDoneFollowVehicle(vehicle.getId());

            long feeRent = 0;
            if(vehicle.getStatus() == ConstantVariableCommon.RENTED_VEHICLE_STATUS){
                feeRent = invoice.caculateManualRentFee();
            }else{
                feeRent = invoice.caculateRentDayFee();
            }

            long amount = feeRent - deposit;

            InterbankInterface interbank = new InterbankSubsystem();
            PaymentTransactionDTO paymentTransaction;
            String method;
            if(amount > 0){
                // neu tien thue lon hon tien coc thi goi api tra tien
                method = "pay";
                paymentTransaction = interbank.pay(card, (int) amount , "Payment Return bike !");
            }else{
                // neu tien coc lon hon tien thue thi goi api hoan tien
                method = "refund";
                paymentTransaction = interbank.refund(card, (int) -amount , "Payment Return bike !");
            }
            saveTransaction(paymentTransaction, method);
            // update vehicle status and parking slot status
            updateVehicleAndParkingSlot(vehicle.getId(), parkingSlotId);
            updateInvoice(invoice.getId());
            // update invoice status
            return ResponseEntity.ok(paymentTransaction);
        }catch (PaymentException | UnrecognizedException ex){
            return ResponseEntity.badRequest().body("Cann't payment");
        }
    }

    private void updateVehicleAndParkingSlot(Integer vehicleId, Integer parkingSlotId) {
        vehicleService.updateVehicleStatusAndParkingSlot(vehicleId, ConstantVariableCommon.NOT_RENT_VEHICLE_STATUS, parkingSlotId);
        parkingSlotService.updateParkingSLotStatus(parkingSlotId, ConstantVariableCommon.OCCUPIED_SLOT_STATUS);
    }

    private void updateInvoice(Integer invoiceId) {
        invoiceService.updateInvoiceStatus(invoiceId, ConstantVariableCommon.DONE_INVOICE);
    }

    /**
     * Tinh toan so tien thue xe
     * @param timeStart: thoi gian bat dau thue
     * @param timeEnd: thoi gian ket thuc thue
     * @param maxTime: thoi gian thue xe toi da ban dau
     * @return chi phi thue xe da tinh toan
     * @throws ParseException
     */
      public static Long calculateFeeRentBike(String timeStart, String timeEnd, long maxTime) throws ParseException {
          DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
          Date dateStart = df.parse(timeStart);
          Date dateEnd = df.parse(timeEnd);
          // tien thue xe
          Long rentFee = null;
          Long timeRentMillis = dateEnd.getTime() - dateStart.getTime();
          Long timeRentMinutes = TimeUnit.MILLISECONDS.toMinutes(timeRentMillis);
          Long timeRentHours = TimeUnit.MILLISECONDS.toHours(timeRentMillis);
          if(timeRentHours < 12){
              rentFee = RENT_FEE_A_PART_DAY - (12 - timeRentHours)*10000;
              return rentFee;
          }
          Long timeOver = maxTime - timeRentMinutes;
          rentFee = Math.round((double) timeRentHours / 12)*RENT_FEE_A_PART_DAY + Math.round(timeOver / 15)*2000;
          return rentFee;
      }

      @GetMapping("/get-parking-slot/{stationId}/{type}")
    public ResponseEntity<Object> getParkingSlot(@PathVariable(name = "stationId")Integer stationId, @PathVariable(name = "type")Integer type){
          return ResponseEntity.ok(parkingSlotService.getBlankSlotByType(stationId, type));
      }

    private void saveTransaction(PaymentTransactionDTO paymentTransaction, String method) {
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setContent(paymentTransaction.getTransactionContent());
        transaction.setAmount(paymentTransaction.getAmount());
        // transaction.setCreatedAt(Long.parseLong(paymentTransaction.getCreatedAt()));
        transaction.setMethod(method);
        transaction.setErrorCode(paymentTransaction.getErrorCode());
        PaymentTransaction trans = paymentTransactionService.saveTransaction(transaction);
    }
}
