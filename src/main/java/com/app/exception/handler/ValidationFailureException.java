package com.app.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationFailureException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValidationFailureException(String message){
		super(message);
	}
}
