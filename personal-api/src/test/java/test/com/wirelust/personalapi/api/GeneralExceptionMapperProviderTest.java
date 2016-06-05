package test.com.wirelust.personalapi.api;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.wirelust.personalapi.api.providers.GeneralExceptionMapperProvider;
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
public class GeneralExceptionMapperProviderTest {

	@Inject
	GeneralExceptionMapperProvider generalExceptionMapperProvider;

	@Test
	public void shouldBeAbleToMapException() {
		Exception exception = new Exception("test exception");
		Response response = generalExceptionMapperProvider.toResponse(exception);

		Assert.assertEquals(EnumErrorCode.GENERIC_ERROR.responseStatus().getStatusCode(), response.getStatus());

		Assert.assertTrue(response.getEntity() instanceof ApiErrorType);
		if (response.getEntity() instanceof ApiErrorType) {
			ApiErrorType apiError = (ApiErrorType)response.getEntity();
			Assert.assertEquals("Generic error",  apiError.getText());
		}
	}

}
