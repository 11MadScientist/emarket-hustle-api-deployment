package org.emarket.hustle.hustleemarketrest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler
{

	private String message;

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(NotFoundException e)
	{
		ErrorResponse error = new ErrorResponse();

		message = e.getMessage() + " WAS NOT FOUND!";

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(message);
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(UniqueErrorException e)
	{
		ErrorResponse error = new ErrorResponse();

		message = e.getMessage() + " SHOULD BE UNIQUE!";

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(message);
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(ErrorLoginException e)
	{
		ErrorResponse error = new ErrorResponse();

		message = e.getMessage() + " DOES NOT MATCH!";

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(message);
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(NotPermittedException e)
	{
		ErrorResponse error = new ErrorResponse();

		message = e.getMessage() + " IS NOT PERMITTED!";

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(message);
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(FailedException e)
	{
		ErrorResponse error = new ErrorResponse();

		message = e.getMessage() + " FAILED!";

		error.setStatus(HttpStatus.EXPECTATION_FAILED.value());
		error.setMessage(message);
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception e)
	{
		ErrorResponse error = new ErrorResponse();

		message = "BAD REQUEST! " + e.getMessage();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(message);
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
