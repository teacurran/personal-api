package test.com.wirelust.personalapi.api;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wirelust.personalapi.api.providers.JacksonConfigurationProvider;
import com.wirelust.personalapi.api.v1.representations.AccountType;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Date: 05-Jun-2016
 *
 * @author T. Curran
 */
public class JacksonConfigurationProviderTest {

	@Test
	public void shouldBeAbleToConfigureJackson() {
		JacksonConfigurationProvider jacksonConfigurationProvider = new JacksonConfigurationProvider();
		ObjectMapper objectMapper = jacksonConfigurationProvider.locateMapper(AccountType.class,
			MediaType.APPLICATION_JSON_TYPE);
		DeserializationConfig deserializationConfig = objectMapper.getDeserializationConfig();

		assertFalse(deserializationConfig.isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE));
		assertTrue(deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
	}
}
