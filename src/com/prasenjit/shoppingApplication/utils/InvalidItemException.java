/**
 * This exception is used whenever an exception
 * occurs while parsing a line. The exception is caught 
 * and the line is skipped and moved to next item.
 * @author prasenjit das
 */
package com.prasenjit.shoppingApplication.utils;

public class InvalidItemException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception sourceException;
	
	public InvalidItemException(String msg) {
		super(msg);
	}

	public Exception getSourceException() {
		return sourceException;
	}

	public void setSourceException(Exception sourceException) {
		this.sourceException = sourceException;
	}
}
