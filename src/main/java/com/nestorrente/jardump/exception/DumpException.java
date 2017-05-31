package com.nestorrente.jardump.exception;

public class DumpException extends RuntimeException {

	private static final long serialVersionUID = -7472492718589073250L;

	public DumpException() {
		super();
	}

	public DumpException(String message) {
		super(message);
	}

	public DumpException(Throwable cause) {
		super(cause);
	}

	public DumpException(String message, Throwable cause) {
		super(message, cause);
	}

}
