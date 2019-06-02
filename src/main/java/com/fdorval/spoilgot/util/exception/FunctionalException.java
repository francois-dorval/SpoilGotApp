package com.fdorval.spoilgot.util.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FunctionalException extends Exception {
	

	private static final long serialVersionUID = 1L;

	Logger LOG = LoggerFactory.getLogger(FunctionalException.class);
	
	
	FunctionalException(Exception e){
		super(e);
	}
	
	FunctionalException(String message, Exception e){
		super(message, e);
	}
	
	public FunctionalException(String message){
		super(message);
	}


	/**
	 * throws an exception
	 * @param message
	 * @param e
	 * @throws FunctionalException
	 */
	public static void throwFunctionalExceptionException(String message, Exception e) throws FunctionalException {
		throw new FunctionalException(message, e);
	}
	
	/**
	 * throws an exception
	 * @param message
	 * @param e
	 * @throws FunctionalException
	 */
	public static void throwFunctionalException(String message) throws FunctionalException {
		throw new FunctionalException(message);
	}
}
