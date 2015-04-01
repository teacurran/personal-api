package com.wirelust.personalapi.api.providers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wirelust.personalapi.api.exceptions.ApplicationException;
import com.wirelust.personalapi.api.v1.representations.ApplicationError;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.api.v1.representations.ParameterErrorType;
import com.wirelust.personalapi.locales.I18n;
import com.wirelust.personalapi.qualifiers.Localization;
import com.wirelust.personalapi.services.Configuration;
import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 28-03-2015
 *
 * @author T. Curran
 */
@Provider
public class ValidationExceptionMapperProvider implements ExceptionMapper<ValidationException> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionMapperProvider.class);

	@Inject
	Configuration configuration;

	@Inject
	JsfMessage<I18n> messages;

	@Inject
	@Localization
	transient protected ResourceBundle locale;

	private Exception resolveCause(final Exception inException) {

		// Unwrap container exception?
		if (inException instanceof WebApplicationException) {
			// Is there an associated cause?
			if ((inException.getCause() != null) && (inException.getCause() instanceof Exception)) {
				// Return the cause
				return (Exception) inException.getCause();
			}
		}

		return inException;
	}

	@Override
	public Response toResponse(final ValidationException inException) {

		LOGGER.debug("Mapping exception to response: ", inException);

		final Response response;

		final ApplicationError applicationError = new ApplicationError();

		final Exception cause = this.resolveCause(inException);

		// try to find the root class
		Method method = null;
		for (StackTraceElement stackTraceElement : inException.getStackTrace()) {
			String className = stackTraceElement.getClassName();

			if (className.startsWith("com.wirelust.personalapi.api.v1.resources.")) {
				// strip off everything before the $ in the case that the class was proxied
				if (className.contains("$")) {
					className = className.substring(0, className.indexOf("$"));
				}
				try {
					Class causeClass = Class.forName(className);
					// look for the first method of the name.
					// warning: this means that all of our resource methods have to be unique
					// overloaded method names will cause us to get the wrong one.
					for (Method thisMethod : causeClass.getMethods()) {
						if (thisMethod.getName().equals(stackTraceElement.getMethodName())) {
							method = thisMethod;
							break;
						}
					}
				} catch (ClassNotFoundException cnfe) {
					LOGGER.error("unable to find class:{}", className);
				}
				break;
			}
		}

		if (inException instanceof ResteasyViolationException) {

			ResteasyViolationException rve = (ResteasyViolationException) inException;

			applicationError.setCode(EnumErrorCode.ILLEGAL_ARGUMENT_ERROR);

			for (ResteasyConstraintViolation rcv : rve.getParameterViolations()) {

				ParameterErrorType parameterErrorType = new ParameterErrorType();
				parameterErrorType.setParameter(rcv.getPath());
				parameterErrorType.setMessage(rcv.getMessage());

				// attempt to get the parameter name
				if (method != null) {

					Integer parameterNumber = null;
					if (rcv.getPath().startsWith("info.arg")) {
						try {
							parameterNumber = Integer.parseInt(rcv.getPath().substring("info.arg".length()));
						} catch (NumberFormatException nfe) {
							LOGGER.warn("unable to get parameter number:{}", rcv.getPath());
						}
					}

					if (parameterNumber != null) {
						Annotation[][] annotations = method.getParameterAnnotations();
						if (annotations.length >= parameterNumber+1) {
							Annotation[] parameterAnnotations = annotations[parameterNumber];
							for (Annotation annotation : parameterAnnotations) {
								if (annotation instanceof FormParam) {
									FormParam formParam = (FormParam) annotation;
									parameterErrorType.setParameter(formParam.value());
								}
								if (annotation instanceof QueryParam) {
									QueryParam queryParam = (QueryParam) annotation;
									parameterErrorType.setParameter(queryParam.value());
								}
							}
						}
					}
				}

				applicationError.addParameterError(parameterErrorType);
			}
		}

		// TODO: look up error text from configuration
		EnumErrorCode errorCode = applicationError.getCode();
		if (errorCode == null) {
			errorCode = EnumErrorCode.GENERIC_ERROR;
		}

		applicationError.setText(locale.getString("api.v1.error." + errorCode.value()));

		LOGGER.debug("Initialized application error response: code=[{}] message=[{}]", applicationError
				.getCode(), applicationError.getDetail());

		// Initialize the entity response
		response = Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(applicationError).build();

		LOGGER.debug("Returning error response: status=[{}] entity=[{}]", new Object[]{response.getStatus(), response
				.getEntity() == null ? null : response.getEntity().getClass()});

		return response;
	}

	public static int resolveStatus(final EnumErrorCode inErrorCode) {

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
