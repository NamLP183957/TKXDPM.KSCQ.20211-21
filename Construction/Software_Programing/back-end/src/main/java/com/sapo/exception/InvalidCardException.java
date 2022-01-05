package com.sapo.exception;

import com.sapo.exception.PaymentException;

public class InvalidCardException extends PaymentException {
	public InvalidCardException() {
		super("ERROR: Invalid card!");
	}
}
