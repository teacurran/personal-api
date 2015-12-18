package com.wirelust.personalapi.api.providers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wirelust.personalapi.api.v1.representations.ApplicationErrorType;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.api.v1.representations.ParameterErrorType;
import com.wirelust.personalapi.locales.I18n;
import com.wirelust.personalapi.qualifiers.Localization;
import com.wirelust.personalapi.services.Configuration;
import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
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
	private static final String METHOD_CAUSE_PACKAGE = "com.wirelust.personalapi.api.v1.resources.";
	private static Pattern ARGUMENT_PATTERN = Pattern.compile("^(.*)\\..*(\\d)$");

	@Inject
	Configuration configuration;

	@Inject
	JsfMessage<I18n> messages;

	@Inject
	@Localization
	protected ResourceBundle locale;

	@Override
	public Response toResponse(final ValidationException inException) {

		final Response response;

		final ApplicationErrorType applicationError = new ApplicationErrorType();


		if (inException instanceof ResteasyViolationException) {
			handleResteasyViolation((ResteasyViolationException)inException, applicationError);
		}

		// TODO: look up error text from configuration
		EnumErrorCode errorCode = applicationError.getCode();
		if (errorCode == null) {
			errorCode = EnumErrorCode.GENERIC_ERROR;
		}

		applicationError.setText(locale.getString("api.v1.error." + errorCode.value()));

		// Initialize the entity response
		response = Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(applicationError).build();

		LOGGER.debug("Returning error: status=[{}] code=[{}] message=[{}]", response.getStatus(), applicationError
			.getCode(), applicationError.getDetail());

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

	private Method resolveMethodCause(final Exception inException) {
		for (StackTraceElement stackTraceElement : inException.getStackTrace()) {
			String className = stackTraceElement.getClassName();

			if (!className.startsWith(METHOD_CAUSE_PACKAGE)) {
				continue;
			}

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
						return thisMethod;
					}
				}
				return null;
			} catch (ClassNotFoundException cnfe) {
				LOGGER.error("unable to find class:{}", className, cnfe);
			}
		}
		return null;
	}

	private void handleResteasyViolation(final ResteasyViolationException inException,
										 final ApplicationErrorType inError) {

		inError.setCode(EnumErrorCode.ILLEGAL_ARGUMENT_ERROR);

		for (ResteasyConstraintViolation rcv : inException.getParameterViolations()) {

			ParameterErrorType parameterErrorType = new ParameterErrorType();
			parameterErrorType.setParameter(rcv.getPath());
			parameterErrorType.setMessage(rcv.getMessage());
			inError.addParameterError(parameterErrorType);

			// try to find the root class
			Method method = resolveMethodCause(inException);
			Integer parameterNumber = extractParameterNumber(rcv);

			if (method == null || parameterNumber == null) {
				continue;
			}

			String parameterName = getParameterName(method, parameterNumber);
			if (parameterName != null) {
				parameterErrorType.setParameter(parameterName);
			}

			LOGGER.debug("Parameter error:{} message:{}", parameterErrorType.getParameter(),
				parameterErrorType.getMessage());
		}
	}

	/**
	 * Extracts the method parameter from the ResteasyConstraintViolation
	 * This exception returns with a path that looks like "register.arg2"
	 * we want to just return the number 2.
	 *
	 * @param inRcv
	 * @return
	 */
	private Integer extractParameterNumber(ResteasyConstraintViolation inRcv) {
		Matcher matcher = ARGUMENT_PATTERN.matcher(inRcv.getPath());

		if (matcher.matches()) {
			try {
				return Integer.parseInt(matcher.group(2));
			} catch (NumberFormatException nfe) {
				LOGGER.warn("unable to get parameter number:{}", inRcv.getPath());
			}
		}
		return null;
	}

	private String getParameterName(final Method inMethod, final int inParameterNumber) {
		// if we know the parameter number, we can use reflection to
		// attempt to find the @FormParam or @QueryParam name of the parameter
		Annotation[][] annotations = inMethod.getParameterAnnotations();
		if (annotations.length >= inParameterNumber+1) {
			Annotation[] parameterAnnotations = annotations[inParameterNumber];
			for (Annotation annotation : parameterAnnotations) {
				if (annotation instanceof FormParam) {
					FormParam formParam = (FormParam) annotation;
					return formParam.value();
				}
				if (annotation instanceof QueryParam) {
					QueryParam queryParam = (QueryParam) annotation;
					return queryParam.value();
				}
			}
		}
		return null;
	}


}
