package com.wirelust.personalapi.api.v1;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.wirelust.personalapi.api.providers.JacksonConfigurationProvider;
import com.wirelust.personalapi.api.v1.resources.AccountResource;


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

		return classes;
	}
}
