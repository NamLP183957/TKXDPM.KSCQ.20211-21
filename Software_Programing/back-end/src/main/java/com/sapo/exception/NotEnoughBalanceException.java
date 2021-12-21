package com.sapo.exception;

import com.sapo.exception.PaymentException;

public class NotEnoughBalanceException extends PaymentException {

	public NotEnoughBalanceException() {
		super("ERROR: Not enough balance in card!");
	}

}
