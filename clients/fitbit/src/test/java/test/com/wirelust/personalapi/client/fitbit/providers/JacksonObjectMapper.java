package test.com.wirelust.personalapi.client.fitbit.providers;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**
 * Date: 7/3/13
 *
 * @author T. Curran
 */
public class JacksonObjectMapper extends ObjectMapper {

	public static JacksonObjectMapper get() {

		// Jackson 2.0
		JacksonObjectMapper mapper = new JacksonObjectMapper();

		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return mapper;
	}


}
