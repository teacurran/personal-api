package com.wirelust.personalapi.api.exceptions;

import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;

/**
 * Date: 13-03-2015
 *
 * @Author T. Curran
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 2510224909561211312L;

	private final EnumErrorCode errorCode;

	public ApiException(
		final EnumErrorCode inErrorCode) {

		this.errorCode = inErrorCode;
	}

	public ApiException(
		final EnumErrorCode inErrorCode,
		final Throwable inCause ) {

		super( inErrorCode.name(), inCause );

		this.errorCode = inErrorCode;
	}

	public EnumErrorCode getErrorCode() {

		return this.errorCode;
	}

}
