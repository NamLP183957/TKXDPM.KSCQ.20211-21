package com.sapo.exception;

import com.sapo.exception.PaymentException;

public class InvalidVersionException extends PaymentException {
	public InvalidVersionException() {
		super("ERROR: Invalid Version Information!");
	}
}
