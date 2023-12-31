package com.booking.exception;

import com.booking.utilities.Constants;

public class OrderAPIException extends Exception {

	private static final long serialVersionUID = 5549901642950534444L;

	private final Constants.ErrorResponse errorResponse;
	private final Boolean isSevere;

	public OrderAPIException(Constants.ErrorResponse errorResponse, Boolean isSevere) {
		super();
		this.errorResponse = errorResponse;
		this.isSevere = isSevere;
	}

	public Constants.ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public Boolean getIsSevere() {
		return isSevere;
	}

}
