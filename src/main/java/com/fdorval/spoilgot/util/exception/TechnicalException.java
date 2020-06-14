package com.fdorval.spoilgot.util.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TechnicalException extends Exception {


    private static final long serialVersionUID = 1L;

    Logger LOG = LoggerFactory.getLogger(TechnicalException.class);


    TechnicalException(Exception e) {
        super(e);
    }

    TechnicalException(String message, Exception e) {
        super(message, e);
    }

    public TechnicalException(String message) {
        super(message);
    }


    /**
     * throws an exception
     *
     * @param message
     * @param e
     * @throws TechnicalException
     */
    public static void throwTechnicalException(String message, Exception e) throws TechnicalException {
        throw new TechnicalException(message, e);
    }

    /**
     * throws an exception
     *
     * @param message
     * @throws TechnicalException
     */
    public static void throwTechnicalException(String message) throws TechnicalException {
        throw new TechnicalException(message);
    }
}
