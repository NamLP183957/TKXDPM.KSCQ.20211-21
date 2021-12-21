package com.sapo.subsystem.interbank;

import com.sapo.entities.Card;
import com.sapo.entities.PaymentTransaction;
import com.sapo.utils.Configs;
import com.sapo.utils.MyMap;
import com.sapo.utils.Utils;

import java.util.Map;

public class InterbankSubsystemController {
    private static final String PAY_COMMAND = "pay";
    private static final String REFUND_COMMAND = "refund";
    private static final String VERSION = "1.0.1";
    private static final String APP_CODE = "BMRZKqw9EOk=";
    private static final String SECRET_KEY = "BjVrvzaFJ9w=";
    private static InterbankBoundary interbankBoundary = new InterbankBoundary();
    private static String token = "";

    public PaymentTransaction pay(Card card, int amount, String contents) {
        Map<String, Object> transaction = new MyMap();

        try {
            transaction.putAll(MyMap.toMyMap(card));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            //throw new InvalidCardException();
            e.printStackTrace();
        }
        transaction.put("command", PAY_COMMAND);
        transaction.put("transactionContent", contents);
        transaction.put("amount", amount);
        transaction.put("createdAt", Utils.getToday());

        Map<String, Object> requestMap = new MyMap();
        requestMap.put("version", VERSION);
        requestMap.put("transaction", transaction);

        String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap), token);
        MyMap response = null;
        try {
            response = MyMap.toMyMap(responseText, 0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            //throw new UnrecognizedException();
        }

        return makePaymentTransaction(response);
    }

    private String generateData(Map<String, Object> data) {
        return ((MyMap) data).toJSON();
    }


    private PaymentTransaction makePaymentTransaction(MyMap response) {
        if (response == null)
            return null;
        MyMap transcation = (MyMap) response.get("transaction");
        Card card = new Card(
                (String) transcation.get("cardCode"),
                (String) transcation.get("owner"),
                Integer.parseInt((String) transcation.get("cvvCode")),
                (String) transcation.get("dateExpired"));
        PaymentTransaction trans = new PaymentTransaction();
//        PaymentTransaction trans = new PaymentTransaction(
//                (String) response.get("errorCode"),
//                card,
//                (String) transcation.get("transactionId"),
//                (String) transcation.get("transactionContent"),
//                Integer.parseInt((String) transcation.get("amount")),
//                (String) transcation.get("createdAt"));
//
//        switch (trans.getErrorCode()) {
//            case "00":
//                break;
//            case "01":
//                throw new InvalidCardException();
//            case "02":
//                throw new NotEnoughBalanceException();
//            case "03":
//                throw new InternalServerErrorException();
//            case "04":
//                throw new SuspiciousTransactionException();
//            case "05":
//                throw new NotEnoughTransactionInfoException();
//            case "06":
//                throw new InvalidVersionException();
//            case "07":
//                throw new InvalidTransactionAmountException();
//            default:
//                throw new UnrecognizedException();
//        }

        return trans;
    }
}
