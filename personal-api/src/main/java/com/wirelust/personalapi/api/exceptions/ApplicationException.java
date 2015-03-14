package com.wirelust.personalapi.api.exceptions;

import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;

/**
 * Date: 13-03-2015
 *
 * @Author T. Curran
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 2510224909561211312L;

	private EnumErrorCode errorCode;

	public ApplicationException() {

		super();
	}

	public ApplicationException(
		final EnumErrorCode inErrorCode) {

		this.errorCode = inErrorCode;
	}

	public ApplicationException(
		final EnumErrorCode inErrorCode,
		final String inMessage) {

		super(inMessage, null);

		this.errorCode = inErrorCode;
	}

	public ApplicationException(
		final String inMessage ) {

		super( inMessage );
	}

	public ApplicationException(
		final String inMessage,
		final Throwable inCause ) {

		super( inMessage, inCause );
	}

	public ApplicationException(
		final EnumErrorCode inErrorCode,
		final String inMessage,
		final Throwable inCause ) {

		super( inMessage, inCause );

		this.errorCode = inErrorCode;
	}

	public ApplicationException(
		final Throwable inCause ) {

		super( inCause );
	}

	public EnumErrorCode getErrorCode() {

		return this.errorCode;
	}

	public void setErrorCode(EnumErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
