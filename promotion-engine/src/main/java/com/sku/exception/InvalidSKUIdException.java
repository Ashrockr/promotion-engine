package com.sku.exception;

/**
 * Exception to be thrown when SKUId in not found and there fore is invalid
 *
 * @author ashish
 *
 */
public class InvalidSKUIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidSKUIdException() {
	super();
    }

    public InvalidSKUIdException(final String message, final Throwable cause, final boolean enableSuppression,
	    final boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidSKUIdException(final String message, final Throwable cause) {
	super(message, cause);
	// TODO Auto-generated constructor stub
    }

    public InvalidSKUIdException(final String message) {
	super(message);
    }

    public InvalidSKUIdException(final Throwable cause) {
	super(cause);
    }

}
