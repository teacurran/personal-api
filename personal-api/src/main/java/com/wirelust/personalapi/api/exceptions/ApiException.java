package com.wirelust.personalapi.api.exceptions;

import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;

/**
 * Date: 13-03-2015
 *
 * @Author T. Curran
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 2510224909561211312L;

	private EnumErrorCode errorCode;

	public ApiException() {

		super();
	}

	public ApiException(
		final EnumErrorCode inErrorCode) {

		this.errorCode = inErrorCode;
	}

	public ApiException(
		final EnumErrorCode inErrorCode,
		final String inMessage) {

		super(inMessage, null);

		this.errorCode = inErrorCode;
	}

	public ApiException(
		final String inMessage ) {

		super( inMessage );
	}

	public ApiException(
		final String inMessage,
		final Throwable inCause ) {

		super( inMessage, inCause );
	}

	public ApiException(
		final EnumErrorCode inErrorCode,
		final String inMessage,
		final Throwable inCause ) {

		super( inMessage, inCause );

		this.errorCode = inErrorCode;
	}

	public ApiException(
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
