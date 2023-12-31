package com.booking.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.booking.json.model.GenericResponse;
import com.booking.utilities.Constants;

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger log = Logger.getLogger(ControllerExceptionHandler.class.getName());

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	public GenericResponse handleConstraintViolationException(ConstraintViolationException ex) {
		log.warning(ex.getMessage() + "\n" + ex);
		return new GenericResponse(Constants.ErrorResponse.CONSTRAINT_VIOLATION.getHttpCode(), false,
				ex.getMessage(), Constants.ErrorResponse.CONSTRAINT_VIOLATION.getAppCode(),
				Constants.TIMEZONE_ID);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ResponseBody
	@ExceptionHandler(MissingRequestHeaderException.class)
	public GenericResponse handleConstraintViolationException(MissingRequestHeaderException ex) {
		log.warning(ex.getMessage() + "\n" + ex);
		return new GenericResponse(Constants.ErrorResponse.MISSING_REQUEST_HEADER.getHttpCode(), false,
				ex.getMessage(), Constants.ErrorResponse.MISSING_REQUEST_HEADER.getAppCode(),
				Constants.TIMEZONE_ID);

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public GenericResponse handleGeneralError(Exception ex) {
		log.severe(ex.getMessage() + "\n" + ex + "\n" + Arrays.toString(ex.getStackTrace()));
		return new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
				Constants.ErrorResponse.INTERNAL_SERVER_ERROR.getMessage(),
				Constants.ErrorResponse.INTERNAL_SERVER_ERROR.getAppCode(), Constants.TIMEZONE_ID);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ResponseBody
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public GenericResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		log.warning(ex.getMessage() + "\n" + ex);
		return new GenericResponse(Constants.ErrorResponse.NOT_FOUND.getHttpCode(), false,
				Constants.ErrorResponse.NOT_FOUND.getMessage(),
				Constants.ErrorResponse.NOT_FOUND.getAppCode(), Constants.TIMEZONE_ID);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ResponseBody
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public GenericResponse handleMissingParams(MissingServletRequestParameterException ex) {
		log.warning(ex.getMessage() + "\n" + ex);
		return new GenericResponse(Constants.ErrorResponse.MISSING_SERVLET_REQUEST_PARAMETER.getHttpCode(), false,
				ex.getMessage(), Constants.ErrorResponse.MISSING_SERVLET_REQUEST_PARAMETER.getAppCode(),
				Constants.TIMEZONE_ID);
	}

	@ResponseBody
	@ExceptionHandler(OrderAPIException.class)
	public ResponseEntity<GenericResponse> handleTestAPIException(OrderAPIException ex) {
		Constants.ErrorResponse errorResponse = ex.getErrorResponse();
		if (ex.getIsSevere()) {
			log.severe(errorResponse.getMessage() + "\n" + ex + "\n" + Arrays.toString(ex.getStackTrace()));
		} else {
			log.warning(errorResponse.getMessage());
		}
		GenericResponse response = new GenericResponse(errorResponse.getHttpCode(), false, errorResponse.getMessage(),
				errorResponse.getAppCode(), Constants.TIMEZONE_ID);
		return ResponseEntity.status(errorResponse.getHttpCode()).body(response);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	public GenericResponse handleValidationException(ValidationException ex) {
		log.warning(ex.getMessage() + "\n" + ex);
		return new GenericResponse(Constants.ErrorResponse.VALIDATION_EXCEPTION.getHttpCode(), false,
				ex.getMessage(), Constants.ErrorResponse.VALIDATION_EXCEPTION.getAppCode(),
				Constants.TIMEZONE_ID);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public GenericResponse processValidationError(MethodArgumentNotValidException ex) {

		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ResponseBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public GenericResponse requestNotReadable(HttpMessageNotReadableException ex) {
		log.warning(ex.getMessage() + "\n" + ex);
		return new GenericResponse(HttpStatus.BAD_REQUEST.value(), false,
				Constants.ErrorResponse.INVALID_JSON_FORMAT.getMessage(),
				Constants.ErrorResponse.INVALID_JSON_FORMAT.getAppCode(), Constants.TIMEZONE_ID);
	}

	private GenericResponse processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
		GenericResponse response = new GenericResponse(HttpStatus.BAD_REQUEST.value(), false,
				Constants.ErrorResponse.INVALID_JSON_CONTENT.getMessage(),
				Constants.ErrorResponse.INVALID_JSON_CONTENT.getAppCode(), new HashMap<>(),
				Constants.TIMEZONE_ID);

		for (FieldError fieldError : fieldErrors) {
			response.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
			log.warning(fieldError.getField() + " : " + fieldError.getDefaultMessage());
		}
		return response;
	}

}
