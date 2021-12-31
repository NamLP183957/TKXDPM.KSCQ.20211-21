package com.sapo.services;

import com.sapo.entities.PaymentTransaction;
import org.springframework.stereotype.Service;

@Service
public interface PaymentTransactionService {

    public PaymentTransaction saveTransaction(PaymentTransaction paymentTransaction);
}
