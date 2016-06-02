package com.wirelust.personalapi.exceptions;

/**
 * Date: 7/12/12
 *
 * @author T. Curran
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 2510224909561211312L;

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
			final Throwable inCause) {

		super( inCause );
	}
}
