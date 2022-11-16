package com.example.demo.Exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.DTO_and_ENUM.MyError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyError> adminExceptionHandler(AdminException ae, WebRequest wr){
		
		MyError error= new MyError(ae.getMessage(), wr.getDescription(false), LocalDate.now());
		
		
		return new ResponseEntity<MyError>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyError> userExceptionHandler(UserException ae, WebRequest wr){
		
		MyError error= new MyError(ae.getMessage(), wr.getDescription(false), LocalDate.now());
		
		
		return new ResponseEntity<MyError>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<MyError> categoryExceptionHandler(CategoryException ae, WebRequest wr){
		
		MyError error= new MyError(ae.getMessage(), wr.getDescription(false), LocalDate.now());
		
		
		return new ResponseEntity<MyError>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyError> productExceptionHandler(ProductException ae, WebRequest wr){
		
		MyError error= new MyError(ae.getMessage(), wr.getDescription(false), LocalDate.now());
		
		
		return new ResponseEntity<MyError>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyError> cartExceptionHandler(CartException ae, WebRequest wr){
		
		MyError error= new MyError(ae.getMessage(), wr.getDescription(false), LocalDate.now());
		
		
		return new ResponseEntity<MyError>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyError> orderExceptionHandler(OrderException ae, WebRequest wr){
		
		MyError error= new MyError(ae.getMessage(), wr.getDescription(false), LocalDate.now());
		
		
		return new ResponseEntity<MyError>(error,HttpStatus.GATEWAY_TIMEOUT);
	}
	
	
	
	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<MyError> paymentExceptionHandler(PaymentException ae, WebRequest wr){
		
		MyError error= new MyError(ae.getMessage(), wr.getDescription(false), LocalDate.now());
		
		
		return new ResponseEntity<MyError>(error,HttpStatus.BAD_GATEWAY);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> MethodNotValidExceptionHandler(MethodArgumentNotValidException ae, WebRequest wr){
		
		MyError error= new MyError(ae.getMessage(), wr.getDescription(false), LocalDate.now());
		
		
		return new ResponseEntity<MyError>(error,HttpStatus.BAD_GATEWAY);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyError> ExceptionHandler(Exception ae, WebRequest wr){
		
		MyError error= new MyError(ae.getMessage(), wr.getDescription(false), LocalDate.now());
		
		
		return new ResponseEntity<MyError>(error,HttpStatus.BAD_GATEWAY);
	}
	
	
		
}
