package com.provanava.exceptions;


/** Classe para retornar excessões customizáveis para SQLDataIntegrityConstraintViolationException */
public class DataIntegrityConstraintViolationException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public DataIntegrityConstraintViolationException(String msg) {
		super(msg);
	}
	public DataIntegrityConstraintViolationException(String msg, Throwable cause) {
		super(msg, cause);
	}



}
