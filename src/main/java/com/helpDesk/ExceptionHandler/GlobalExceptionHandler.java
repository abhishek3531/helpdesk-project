package com.helpDesk.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handlerMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(field, message);
		});
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Response> notFound(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		Response resp = new Response(message);
		return new ResponseEntity<Response>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(resourceAlreadyExist.class)
	public ResponseEntity<Response> resourceAlreadyExist(resourceAlreadyExist ex)
	{
		String message=ex.getMessage();
		Response resp = new Response(message);
		return new ResponseEntity<Response>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ChildNotFoundException.class)
	public ResponseEntity<Response> childNotFound(ChildNotFoundException childNotFoundException)
	{
		String message=childNotFoundException.getMessage();
		Response response=new Response(message);
		
		 return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserAlreadyExist.class)
	public ResponseEntity<Response> userAlreadyExist(UserAlreadyExist alreadyExist)
	{
		String message=alreadyExist.getMessage();
		Response response=new Response(message);
		
		return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<Response> userNotFound(UserNotFound userNotFound)
	{
		String message=userNotFound.getMessage();
		Response response=new Response(message);
		return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CodeException.class)
	public ResponseEntity<Response> codeException(CodeException codeException)
	{
		String message=codeException.getMessage();
		Response response=new Response(message);
		return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST); 
	}
}
