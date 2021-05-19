package com.example.client.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.client.exception.ClientServiceException;
import com.example.client.model.ClientError;

@ControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(ClientServiceException.class)
	public ResponseEntity<ClientError> mapException(ClientServiceException ex) {
		ClientError error = new ClientError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<ClientError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
