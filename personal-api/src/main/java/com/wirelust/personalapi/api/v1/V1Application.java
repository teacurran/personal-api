package com.wirelust.personalapi.api.v1;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.wirelust.personalapi.api.providers.ApiExceptionMapperProvider;
import com.wirelust.personalapi.api.providers.GeneralExceptionMapperProvider;
import com.wirelust.personalapi.api.providers.JacksonConfigurationProvider;
import com.wirelust.personalapi.api.providers.ValidationExceptionMapperProvider;
import com.wirelust.personalapi.api.v1.resources.AccountResource;
import com.wirelust.personalapi.api.v1.resources.SwaggerApiListingResource;


/**
 * Date: 26-03-2015
 *
 * @Author T. Curran
 */
@ApplicationPath("/api/v1")
public class V1Application extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();

		classes.add(AccountResource.class);

		classes.add(JacksonConfigurationProvider.class);

		classes.add(ApiExceptionMapperProvider.class);
		classes.add(ValidationExceptionMapperProvider.class);
		classes.add(GeneralExceptionMapperProvider.class);

		classes.add(SwaggerApiListingResource.class);
		classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

		return classes;
	}
}
