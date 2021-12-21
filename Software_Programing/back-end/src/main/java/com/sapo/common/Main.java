package com.sapo.common;

import com.sapo.entities.Card;
import com.sapo.utils.API;
import com.sapo.utils.Configs;
import com.sapo.utils.MyMap;
import com.sapo.utils.Utils;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> transaction = new MyMap();
        transaction.put("command", "abc");
        transaction.put("cardCode", "kscq1_group21_2021");
        transaction.put("owner", "Group 21");
        transaction.put("cvvCode", "899");
        transaction.put("dateExpired", "1125");
        transaction.put("transactionContent", "dat coc");
        transaction.put("amount", "200");

        Map<String, Object> plainText = new MyMap();
        plainText.put("secretKey", "BjVrvzaFJ9w=");
        plainText.put("transaction", transaction);

        String plainTextJSON = ((MyMap)plainText).toJSON();
        String hashCode = Utils.md5(plainTextJSON);
        System.out.println("Hash code: " + hashCode);
        System.out.println("create: " + Utils.getToday());
        Map<String, Object> request = new MyMap();
        transaction.put("createdAt", Utils.getToday());
        request.put("version", "1.0.1");
        request.put("transaction", transaction);
        request.put("appCode", "BMRZKqw9EOk=");
        request.put("hashCode", hashCode);

        String data = ((MyMap)request).toJSON();
        String response = "Reponse";
        try {
            response = API.post(Configs.PROCESS_TRANSACTION_URL, data, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Reponse: " + response);
    }
}
