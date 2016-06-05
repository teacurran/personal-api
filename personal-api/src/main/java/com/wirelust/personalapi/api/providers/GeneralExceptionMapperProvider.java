package com.wirelust.personalapi.api.providers;

import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wirelust.personalapi.api.v1.representations.ApiErrorType;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.qualifiers.Localization;
import com.wirelust.personalapi.services.Configuration;
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

	@Inject
	@Localization
	ResourceBundle localization;

	@Override
	public Response toResponse(
			final Exception inException) {

		LOGGER.debug("Mapping exception to response: ", inException);

		final Response response;

		final ApiErrorType applicationError = new ApiErrorType();

		final Exception cause = this.resolveCause(inException);

		if (inException instanceof NotFoundException) {

			applicationError.setCode(EnumErrorCode.RESOURCE_NOT_FOUND);

		} else {

			applicationError.setCode(EnumErrorCode.GENERIC_ERROR);
			applicationError.setDetail(cause.getMessage());

			LOGGER.error("unmapped exception", cause);
		}

		EnumErrorCode errorCode = applicationError.getCode();
		applicationError.setText(localization.getString("api.v1.error." + errorCode.value()));

		LOGGER.debug(
				"Initialized application error response: code=[{}] message=[{}]",
				applicationError.getCode(),
				applicationError.getDetail());

		// Initialize the entity response
		response = Response.status(applicationError.getCode().responseStatus()).entity(applicationError).build();

		LOGGER.debug(
				"Returning error response: status=[{}] entity=[{}]",
				new Object[]{
						response.getStatus(),
						response.getEntity() == null ? null : response.getEntity().getClass()});

		return response;
	}

	private Exception resolveCause(
			final Exception inException) {

		// Unwrap container exception?
		// Is there an associated cause?
		if (inException instanceof WebApplicationException
			&& inException.getCause() != null
			&& (inException.getCause() instanceof Exception)) {
			// Return the cause
			return (Exception) inException.getCause();
		}

		return inException;
	}

}
