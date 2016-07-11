package test.com.wirelust.personalapi.api;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;

import com.wirelust.personalapi.api.providers.ValidationExceptionMapperProvider;
import com.wirelust.personalapi.api.v1.representations.ApiErrorType;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.api.v1.representations.ParameterErrorType;
import com.wirelust.personalapi.api.v1.resources.AccountResource;
import com.wirelust.personalapi.producers.ResourceBundleProducer;
import org.jboss.resteasy.api.validation.ConstraintType;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;

/**
 * Date: 05-Jun-2016
 *
 * @author T. Curran
 */
public class ValidationExceptionMapperProviderTest {

	@Spy
	ResourceBundle locale;

	@Spy
	ResourceBundleProducer resourceBundleProducer = new ResourceBundleProducer();

	@Mock
	ResteasyViolationException restEasyException;

	@InjectMocks
	ValidationExceptionMapperProvider validationExceptionMapperProvider;

	List<ResteasyConstraintViolation> fieldViolations = new ArrayList<>();

	@Before
	public void init() throws Exception {
		locale = resourceBundleProducer.getLocalization();

		MockitoAnnotations.initMocks(this);

		fieldViolations.add(new ResteasyConstraintViolation(
			ConstraintType.Type.FIELD,
			"getValidation.1",
			"username validation exception",
			"invalid"
		));
		fieldViolations.add(new ResteasyConstraintViolation(
			ConstraintType.Type.FIELD,
			"getValidation.x",
			"bad parameter index",
			"bad parameter"
		));
		fieldViolations.add(new ResteasyConstraintViolation(
			ConstraintType.Type.FIELD,
			"getValidation.5",
			"query param error",
			"aa"
		));
		fieldViolations.add(new ResteasyConstraintViolation(
			ConstraintType.Type.FIELD,
			"getValidation.6",
			"not annotated method",
			"aa"
		));
	}

	@Test
	public void shouldBeAbleToTransformValidationException() {

		Mockito.when(restEasyException.getParameterViolations()).thenReturn(fieldViolations);

		StackTraceElement ste[] = {
			new StackTraceElement(AccountResource.class.getName(), "register", "AccountResource.java", 123)
		};

		Mockito.when(restEasyException.getStackTrace()).thenReturn(ste);

		Response response = validationExceptionMapperProvider.toResponse(restEasyException);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

		if (response.getEntity() instanceof ApiErrorType) {
			ApiErrorType apiError = (ApiErrorType)response.getEntity();
			List<ParameterErrorType> parameterErrors = apiError.getParameterErrors();

			assertEquals("username", parameterErrors.get(0).getParameter());
		}
	}

	@Test
	public void shouldBeAbleToFindProxiedClasses() {

		Mockito.when(restEasyException.getParameterViolations()).thenReturn(fieldViolations);

		StackTraceElement ste[] = {
			new StackTraceElement(AccountResource.class.getName() + "$Proxy",
				"register", "AccountResource.java", 123)
		};

		Mockito.when(restEasyException.getStackTrace()).thenReturn(ste);

		Response response = validationExceptionMapperProvider.toResponse(restEasyException);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

		if (response.getEntity() instanceof ApiErrorType) {
			ApiErrorType apiError = (ApiErrorType)response.getEntity();
			List<ParameterErrorType> parameterErrors = apiError.getParameterErrors();

			assertEquals("username", parameterErrors.get(0).getParameter());
		}
	}

	@Test
	public void shouldNotFindClassNotInProject() throws Exception {

		Mockito.when(restEasyException.getParameterViolations()).thenReturn(fieldViolations);

		StackTraceElement ste[] = {
			new StackTraceElement("class-not-in-project.class", "register", "AccountResource.java", 123)
		};

		Mockito.when(restEasyException.getStackTrace()).thenReturn(ste);

		Response response = validationExceptionMapperProvider.toResponse(restEasyException);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

		if (response.getEntity() instanceof ApiErrorType) {
			ApiErrorType apiError = (ApiErrorType)response.getEntity();
			List<ParameterErrorType> parameterErrors = apiError.getParameterErrors();

			assertEquals("getValidation.1", parameterErrors.get(0).getParameter());
		}
	}

	@Test
	public void shouldNotFindNonExistingClass() throws Exception {

		Mockito.when(restEasyException.getParameterViolations()).thenReturn(fieldViolations);

		StackTraceElement ste[] = {
			new StackTraceElement(
				ValidationExceptionMapperProvider.METHOD_CAUSE_PACKAGE + ".NotClass", "getValidation",
				"NotClass.java", 65)
		};	

		Mockito.when(restEasyException.getStackTrace()).thenReturn(ste);

		Response response = validationExceptionMapperProvider.toResponse(restEasyException);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

		if (response.getEntity() instanceof ApiErrorType) {
			ApiErrorType apiError = (ApiErrorType)response.getEntity();
			List<ParameterErrorType> parameterErrors = apiError.getParameterErrors();

			assertEquals("getValidation.1", parameterErrors.get(0).getParameter());
		}

	}

	@Test
	public void shouldHandleGenericException() throws Exception {
		ValidationException validationException = new ValidationException();

		Response response = validationExceptionMapperProvider.toResponse(validationException);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

		if (response.getEntity() instanceof ApiErrorType) {
			ApiErrorType apiError = (ApiErrorType)response.getEntity();
			assertEquals(EnumErrorCode.GENERIC_ERROR, apiError.getCode());
		}
	}
}
