package com.wirelust.personalapi.producers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import com.wirelust.personalapi.locales.UTF8ResourceBundleControl;
import com.wirelust.personalapi.qualifiers.ClasspathResource;
import com.wirelust.personalapi.qualifiers.Localization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Date: 09-03-2015
 *
 * @Author T. Curran
 */
@ApplicationScoped
public class ResourceBundleProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBundleProducer.class);

	@Produces
	@Localization
	public ResourceBundle getLocalization() {
		Locale locale = Locale.US;

		return ResourceBundle.getBundle("locales.I18n", locale,
				new UTF8ResourceBundleControl());
	}

	@Produces
	@ClasspathResource("")
	Properties loadPropertiesBundle(InjectionPoint injectionPoint) {
		String name = getName(injectionPoint);

		Properties properties = new Properties();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try (InputStream inputStream = classLoader.getResourceAsStream(name)) {
			properties.load(inputStream);
		} catch (IOException | NullPointerException e) {
			LOGGER.error("error loading properties file", e);
		}
		return properties;
	}

	private String getName(InjectionPoint ip) {
		Set<Annotation> qualifiers = ip.getQualifiers();
		for (Annotation qualifier : qualifiers) {
			if (qualifier.annotationType().equals(ClasspathResource.class)) {
				return ((ClasspathResource) qualifier).value();
			}
		}
		throw new IllegalArgumentException("Injection point " + ip + " does not have @Resource qualifier");
	}


}
