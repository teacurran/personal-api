package com.wirelust.personalapi.api.providers;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wirelust.personalapi.api.exceptions.ApplicationException;
import com.wirelust.personalapi.api.v1.representations.ApplicationErrorType;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.services.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 28-03-2015
 *
 * @Author T. Curran
 */
@Provider
public class ApplicationExceptionMapperProvider implements ExceptionMapper<ApplicationException> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionMapperProvider.class);

	@Inject
	Configuration configuration;

	public ApplicationExceptionMapperProvider() {
	}

	@Override
	public Response toResponse(final ApplicationException inException) {

		LOGGER.debug("Mapping exception to response: ", inException);

		final Response response;

		final ApplicationErrorType applicationError = new ApplicationErrorType();


		applicationError.setCode(inException.getErrorCode());
		applicationError.setDetail(inException.getMessage());

		// TODO: look up error text from configuration
		EnumErrorCode errorCode = applicationError.getCode();
		if (errorCode == null) {
			errorCode = EnumErrorCode.GENERIC_ERROR;
		}
		// TODO: get the message from I18N
		//applicationError.setText(configuration.getMessage("api.v1.error." + errorCode.value(), errorCode.name()));

		LOGGER.debug("Initialized application error response: code=[{}] message=[{}]", applicationError.getCode(),
				applicationError.getDetail());

		// Initialize the entity response
		response = Response.status(ApplicationExceptionMapperProvider.resolveStatus(applicationError.getCode()))
				.entity(applicationError).build();

		LOGGER.debug("Returning error response: status=[{}] entity=[{}]", new Object[]{response.getStatus(), response
				.getEntity() == null ? null : response.getEntity().getClass()});

		return response;
	}

	public static int resolveStatus(final EnumErrorCode inErrorCode) {

		final int status;
		switch (inErrorCode) {
			case RESOURCE_NOT_FOUND:
			case OBJECT_NOT_FOUND:
				status = Response.Status.NOT_FOUND.getStatusCode();
				break;
			case REPRESENTATION_PARSE_ERROR:
			case REPRESENTATION_FORMAT_ERROR:
				status = Response.Status.BAD_REQUEST.getStatusCode();
				break;
			case ILLEGAL_ARGUMENT_ERROR:
				status = Response.Status.BAD_REQUEST.getStatusCode();
				break;
			case GENERIC_ERROR:
				status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
				break;
			default:
				status = Response.Status.BAD_REQUEST.getStatusCode();
				break;
		}

		return status;
	}

}
