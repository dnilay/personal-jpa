package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ErrorResponseModel> handleUserNotFoundException(UserNotFoundException e) {
		ErrorResponseModel error = new ErrorResponseModel();
		error.setMesasge(e.getMessage());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.OK).body(error);
	}

}
