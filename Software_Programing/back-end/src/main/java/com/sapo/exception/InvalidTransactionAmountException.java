package com.sapo.exception;

import com.sapo.exception.PaymentException;


public class InvalidTransactionAmountException extends PaymentException {
	public InvalidTransactionAmountException() {
		super("ERROR: Invalid Transaction Amount!");
	}
}
