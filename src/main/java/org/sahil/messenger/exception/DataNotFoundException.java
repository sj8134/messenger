package org.sahil.messenger.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3459018414859123034L;

	public DataNotFoundException(String message){
		super(message);
	}
}
