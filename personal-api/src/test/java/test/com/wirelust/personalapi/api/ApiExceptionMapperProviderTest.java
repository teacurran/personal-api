package test.com.wirelust.personalapi.api;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.wirelust.personalapi.api.exceptions.ApiException;
import com.wirelust.personalapi.api.providers.ApiExceptionMapperProvider;
import com.wirelust.personalapi.api.v1.representations.ApiErrorType;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.producers.ResourceBundleProducer;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Date: 01-Jun-2016
 *
 * @author T. Curran
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(ResourceBundleProducer.class)
public class ApiExceptionMapperProviderTest {

	@Inject
	ApiExceptionMapperProvider apiExceptionMapperProvider;

	@Test
	public void shouldBeAbleToMapException() {
		ApiException exception = new ApiException(EnumErrorCode.EMAIL_EXISTS);
		Response response = apiExceptionMapperProvider.toResponse(exception);

		Assert.assertEquals(EnumErrorCode.EMAIL_EXISTS.responseStatus().getStatusCode(), response.getStatus());

		Assert.assertTrue(response.getEntity() instanceof ApiErrorType);
		if (response.getEntity() instanceof ApiErrorType) {
			ApiErrorType apiError = (ApiErrorType)response.getEntity();
			Assert.assertEquals("The email address you entered already exists",  apiError.getText());
		}
	}

}
