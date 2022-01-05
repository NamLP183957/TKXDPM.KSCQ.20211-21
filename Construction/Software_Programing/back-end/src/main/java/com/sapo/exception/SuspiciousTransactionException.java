package com.sapo.exception;

import com.sapo.exception.PaymentException;

public class SuspiciousTransactionException extends PaymentException {
	public SuspiciousTransactionException() {
		super("ERROR: Suspicious Transaction Report!");
	}
}
