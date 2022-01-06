package com.sapo.services.impl;

import com.sapo.entities.PaymentTransaction;
import com.sapo.repositories.PaymentTransactionRepository;
import com.sapo.services.PaymentTransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentTransactionServiceImpl implements PaymentTransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentTransactionServiceImpl.class.toString());
    private PaymentTransactionRepository paymentTransactionRepository;
    @Override
    public PaymentTransaction saveTransaction(PaymentTransaction paymentTransaction) {
        return paymentTransactionRepository.save(paymentTransaction);
    }
}
