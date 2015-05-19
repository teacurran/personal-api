package com.wirelust.personalapi.api.providers;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wirelust.personalapi.api.exceptions.ApplicationException;
import com.wirelust.personalapi.api.v1.representations.ApplicationErrorType;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.services.Configuration;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 28-03-2015
 *
 * @Author T. Curran
 */
@Provider
public class GeneralExceptionMapperProvider
		implements ExceptionMapper<Exception> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneralExceptionMapperProvider.class);

	@Inject
	Configuration configuration;

	public GeneralExceptionMapperProvider() {
	}

	private Exception resolveCause(
			final Exception inException) {

		// Unwrap container exception?
		if (inException instanceof WebApplicationException) {
			// Is there an associated cause?
			if ((inException.getCause() != null)
					&& (inException.getCause() instanceof Exception)) {
				// Return the cause
				return (Exception) inException.getCause();
			}
		}

		return inException;
	}

	@Override
	public Response toResponse(
			final Exception inException) {

		LOGGER.debug("Mapping exception to response: ", inException);

		final Response response;

		final ApplicationErrorType applicationError = new ApplicationErrorType();

		final Exception cause = this.resolveCause(inException);

		if (inException instanceof NotFoundException) {

			applicationError.setCode(EnumErrorCode.RESOURCE_NOT_FOUND);

		} else if (cause instanceof ApplicationException) {

			final ApplicationException applicationException = (ApplicationException) cause;
			applicationError.setCode(applicationException.getErrorCode());
			applicationError.setDetail(applicationException.getMessage());

		} else {

			applicationError.setCode(EnumErrorCode.GENERIC_ERROR);
			applicationError.setDetail(cause.getMessage());

			LOGGER.error("unmapped exception", cause);
		}

		// TODO: look up error text from configuration
		EnumErrorCode errorCode = applicationError.getCode();
		if (errorCode == null) {
			errorCode = EnumErrorCode.GENERIC_ERROR;
		}
		// TODO: get the message from I18N
		//applicationError.setText(configuration.getMessage("api.v1.error." + errorCode.value(), errorCode.name()));

		LOGGER.debug(
				"Initialized application error response: code=[{}] message=[{}]",
				applicationError.getCode(),
				applicationError.getDetail());

		// Initialize the entity response
		response =
				Response.status(GeneralExceptionMapperProvider.resolveStatus(applicationError.getCode()))
						.entity(applicationError).build();

		LOGGER.debug(
				"Returning error response: status=[{}] entity=[{}]",
				new Object[]{
						response.getStatus(),
						response.getEntity() == null ? null : response.getEntity().getClass()});

		return response;
	}

	public static int resolveStatus(
			final EnumErrorCode inErrorCode) {

		final int status;
		switch (inErrorCode) {
			case RESOURCE_NOT_FOUND:
				status = Response.Status.NOT_FOUND.getStatusCode();
				break;
			case REPRESENTATION_PARSE_ERROR:
			case REPRESENTATION_FORMAT_ERROR:
				status = Response.Status.BAD_REQUEST.getStatusCode();
				break;
			case ILLEGAL_ARGUMENT_ERROR:
				status = Response.Status.BAD_REQUEST.getStatusCode();
				break;
			default:
				status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
				break;
		}

		return status;
	}

}
