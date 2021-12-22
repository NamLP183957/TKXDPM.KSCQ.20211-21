package com.sapo.controllers;

import com.sapo.dto.PaymentTransaction.PaymentTransactionDTO;
import com.sapo.entities.Card;
import com.sapo.services.ParkingSlotService;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.sapo.exception.PaymentException;
import com.sapo.exception.UnrecognizedException;

@RequestMapping("api/returnbike")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
public class ReturnBikeController {
    private static final int RENT_FEE_A_PART_DAY = 200000;
    /**
     * Represent the Interbank subsystem
     */
      private InterbankInterface interbank;

    /**
     * Thuc hien thanh toan tien thue xe cho khach hang
     * @param card: the card nguoi thanh toan
     * @param amount: so tien can thanh toan
     * @param contents: noi dung giao dich
     * @return result: phan hoi giao dich da thuc hien thanh cong hay chua
     */
      @PostMapping("/process-transaction")
      public Map<String, Object> processTransactionReturnBike(@RequestBody Card card, @RequestBody int amount, @RequestBody String contents ){
          Map<String, Object> result = new MyMap();
          result.put("RESULT", "PAYMENT FAILED!");
          try{
              PaymentTransactionDTO transaction = this.interbank.refund(card, amount, contents);
              result.put("RESULT", "PAYMENT SUCCESSFUL!");
              result.put("MESSAGE", "You have successfully paid!");
          }catch (PaymentException | UnrecognizedException ex){
                result.put("MESSAGE", ex.getMessage());
          }

          return result;
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

}
