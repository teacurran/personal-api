package com.wirelust.personalapi.api.providers;

import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wirelust.personalapi.api.exceptions.ApiException;
import com.wirelust.personalapi.api.v1.representations.ApiErrorType;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.qualifiers.Localization;
import com.wirelust.personalapi.services.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 28-03-2015
 *
 * @author T. Curran
 */
@Provider
public class ApiExceptionMapperProvider implements ExceptionMapper<ApiException> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionMapperProvider.class);

	@Inject
	Configuration configuration;

	@Inject
	@Localization
	ResourceBundle localization;

	@Override
	public Response toResponse(final ApiException inException) {

		LOGGER.debug("Mapping exception to response:{}", inException.getErrorCode());

		final ApiErrorType apiErrorType = new ApiErrorType();


		apiErrorType.setCode(inException.getErrorCode());
		apiErrorType.setDetail(inException.getMessage());

		EnumErrorCode errorCode = apiErrorType.getCode();

		apiErrorType.setText(localization.getString("api.v1.error." + errorCode.value()));

		LOGGER.debug("Initialized application error response: code=[{}] message=[{}]", apiErrorType.getCode(),
				apiErrorType.getDetail());

		// Initialize the entity response
		final Response response = Response.status(errorCode.responseStatus()).entity(apiErrorType).build();

		LOGGER.debug("Returning error response: status=[{}] entity=[{}]", new Object[]{response.getStatus(), response
				.getEntity() == null ? null : response.getEntity().getClass()});

		return response;
	}

}
