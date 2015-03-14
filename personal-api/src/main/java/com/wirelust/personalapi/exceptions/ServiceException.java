package com.wirelust.personalapi.exceptions;

/**
 * Date: 7/12/12
 *
 * @author T. Curran
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 2510224909561211312L;

	private long errorCode = 0;

	public ServiceException() {

		super();
	}

	public ServiceException(
			final String inMessage) {

		super( inMessage );
	}

	public ServiceException(
			final String inMessage,
			final Throwable inCause) {

		super( inMessage, inCause );
	}

	public ServiceException(
			final long inErrorCode,
			final String inMessage,
			final Throwable inCause) {

		super( inMessage, inCause );

		this.errorCode = inErrorCode;
	}

	public ServiceException(
			final Throwable inCause) {

		super( inCause );
	}

	public long getErrorCode() {

		return this.errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
}
