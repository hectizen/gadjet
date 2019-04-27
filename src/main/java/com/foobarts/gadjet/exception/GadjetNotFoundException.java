package com.foobarts.gadjet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GadjetNotFoundException extends RuntimeException {
	public GadjetNotFoundException(String message) {
		super(message);
	}
}
