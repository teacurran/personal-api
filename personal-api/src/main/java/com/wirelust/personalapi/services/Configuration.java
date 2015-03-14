package com.wirelust.personalapi.services;

import java.io.Serializable;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wirelust.personalapi.qualifiers.ClasspathResource;
import com.wirelust.personalapi.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 14-03-2015
 *
 * @Author T. Curran
 */
@Named
@ApplicationScoped
public class Configuration implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

	@Inject
	@ClasspathResource("configuration.properties")
	Properties defaultProperties;

	public Configuration() {

	}

	public String getSetting(final String key) {

		if (this.defaultProperties.containsKey(key)) {
			return this.defaultProperties.getProperty(key);
		}
		return null;
	}

	public String getSetting(final String key, final String defaultValue) {

		final String returnValue = this.getSetting(key);
		if (StringUtils.isEmpty(returnValue)) {
			return defaultValue;
		}
		return returnValue;
	}

	public int getSettingInt(final String key) {

		final String resultString = this.getSetting(key);
		int resultInt = 0;
		try {
			resultInt = Integer.parseInt(resultString);
		} catch (final NumberFormatException e) {
			// do nothing
			LOGGER.warn("Attempt to cast setting as int failed. key:'{}'", key);
		}
		return resultInt;
	}

	public int getSettingInt(final String key, final int defaultValue) {

		final String resultString = this.getSetting(key);
		int resultInt = 0;
		try {
			resultInt = Integer.parseInt(resultString);
		} catch (final NumberFormatException e) {
			resultInt = defaultValue;
		}
		return resultInt;
	}

	public boolean getSettingBool(final String key) {
		return getSettingBool(key, false);
	}

	public boolean getSettingBool(final String key, final boolean defaultValue) {

		String resultString = getSetting(key);
		boolean resultBool;
		try {
			resultBool = Boolean.parseBoolean(resultString);
		} catch (NumberFormatException e) {
			resultBool = defaultValue;
		}
		return resultBool;
	}


}