package com.DAL.Repository.Exception;

public class KeyConstraintException extends InvariantException{

	private static final long serialVersionUID = 1L;

	public KeyConstraintException(String message) {
		super(message);
	}
}
