package com.sapo.subsystem;

import com.sapo.dto.PaymentTransaction.PaymentTransactionDTO;
import com.sapo.entities.Card;
import com.sapo.exception.PaymentException;
import com.sapo.exception.UnrecognizedException;
import com.sapo.subsystem.interbank.InterbankSubsystemController;

public class InterbankSubsystem implements InterbankInterface{

    private InterbankSubsystemController ctrl;

    public InterbankSubsystem() {
        this.ctrl = new InterbankSubsystemController();
    }
    @Override
    public PaymentTransactionDTO pay(Card card, int amount, String contents) throws PaymentException, UnrecognizedException {
        PaymentTransactionDTO transaction = ctrl.pay(card, amount, contents);
        return transaction;
    }

    @Override
    public PaymentTransactionDTO refund(Card card, int amount, String contents) throws PaymentException, UnrecognizedException {
        PaymentTransactionDTO transaction = ctrl.refund(card, amount, contents);
        return transaction;
    }
}
