package com.sapo.subsystem.interbank;

import com.sapo.exception.UnrecognizedException;
import com.sapo.utils.API;

public class InterbankBoundary {
    String query(String url, String token, String data) {
        String response = null;
        try {
            response = API.post(url, data, token);
        } catch (Exception ex) {
            //throw new UnrecognizedException();
            ex.printStackTrace();
        }
        return response;
    }
}
