package com.wirelust.personalapi.api.providers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Date: 26-03-2015
 *
 * @Author T. Curran
 */
public class JacksonObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1800750500920847139L;

	public static JacksonObjectMapper get() {

		// Jackson 2.0
		JacksonObjectMapper mapper = new JacksonObjectMapper();

		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

		return mapper;
	}


}
