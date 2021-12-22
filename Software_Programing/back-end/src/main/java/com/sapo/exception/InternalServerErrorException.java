package com.sapo.exception;

import com.sapo.exception.PaymentException;

public class InternalServerErrorException extends PaymentException {

	public InternalServerErrorException() {
		super("ERROR: Internal Server Error!");
	}

}
