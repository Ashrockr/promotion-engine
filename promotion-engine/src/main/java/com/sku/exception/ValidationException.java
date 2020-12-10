package com.sku.exception;

/**
 * Custom exception for validation related error
 *
 * @author ashish
 *
 */
public class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    public ValidationException() {
	super();
    }

    public ValidationException(final String message, final Throwable cause, final boolean enableSuppression,
	    final boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public ValidationException(final String message, final Throwable cause) {
	super(message, cause);
    }

    public ValidationException(final String message) {
	super(message);
    }

    public ValidationException(final Throwable cause) {
	super(cause);
    }

}
