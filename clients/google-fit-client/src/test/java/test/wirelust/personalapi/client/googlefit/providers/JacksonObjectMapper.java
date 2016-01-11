package test.wirelust.personalapi.client.googlefit.providers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Date: 7/3/13
 *
 * @author T. Curran
 */
public class JacksonObjectMapper extends ObjectMapper {

	public static JacksonObjectMapper get() {

		// Jackson 2.0
		JacksonObjectMapper mapper = new JacksonObjectMapper();

		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

		return mapper;
	}


}
