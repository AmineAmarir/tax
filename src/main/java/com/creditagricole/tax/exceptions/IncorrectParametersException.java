package com.creditagricole.tax.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.creditagricole.tax.common.Constants;

/**
 * The Class IncorrectParametersException.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectParametersException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4264547781021105368L;

	/**
	 * Instantiates a new incorrect parameters exception.
	 */
	public IncorrectParametersException() {
		super(Constants.INCORRECT_PARAMETERS);
	}

}
