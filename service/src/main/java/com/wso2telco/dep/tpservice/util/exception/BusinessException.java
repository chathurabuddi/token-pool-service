package com.wso2telco.dep.tpservice.util.exception;

public  class BusinessException extends Exception {

	/**
	the serialVersionUID is a unique identifier for Serializable classes.
	This is used during the deserialization of an object, to ensure that a loaded class is compatible with the serialized object.
	If no matching class is found, an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = -5524636629189260363L;
	
	private ThrowableError error;
	
	public BusinessException(ThrowableError error) {
		super(error.getMessage());
		this.error = error;
	}
	
	public ThrowableError getErrorType() {
		return this.error;
	}

}
