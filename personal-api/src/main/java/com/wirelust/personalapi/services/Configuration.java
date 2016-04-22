package com.wirelust.personalapi.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wirelust.personalapi.exceptions.ServiceException;
import com.wirelust.personalapi.qualifiers.ClasspathResource;
import com.wirelust.personalapi.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 14-03-2015
 *
 * @author T. Curran
 */
@Named
@ApplicationScoped
public class Configuration implements Serializable {

	private static final long serialVersionUID = -3221266624481566406L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

	private static final String ENV_FILE_NAME = "app.personalapi.env";

	@Inject
	@ClasspathResource("defaults.properties")
	Properties defaultProperties;

	Properties configuredProperties = new Properties();

	Boolean fitbitSync;
	String fitbitSchedule;

	public Configuration() {

	}

	@PostConstruct
	public void init() {
		// load default properties
		bindProperties(defaultProperties, "default");

		String configFileName = System.getProperty(ENV_FILE_NAME);
		LOGGER.info("{}={}", ENV_FILE_NAME, configFileName);

		if (configFileName == null) {
			LOGGER.warn("{} was not specified. using defaults only");
			return;
		}

		InputStream configInputStream = null;
		File propertyFile = new File(configFileName);

		// Attempt to load the config from a file
		if (propertyFile.exists() && propertyFile.canRead()) {
			try {
				configInputStream = new FileInputStream(propertyFile);
			} catch (FileNotFoundException e) {
				// impossible to get here
				LOGGER.error("config file not found", e);
			}
		} else {
			configInputStream = this.getClass().getResourceAsStream("/environments/" + configFileName);
		}

		if (configInputStream == null) {
			throw new ServiceException("Error initializing config, unable to load property file:" + configFileName);
		}

		try {
			configuredProperties.load(configInputStream);
			bindProperties(configuredProperties, "configured");
		} catch (IOException e) {
			throw new ServiceException(e);
		} finally {
			try {
				configInputStream.close();
			} catch (IOException ioe) {
				// nothing we can really do here
				LOGGER.error("error closing input stream", ioe);
			}
		}
		LOGGER.info("env properties loaded:{}", configuredProperties.toString());
	}

	public String getSetting(final String key) {

		if (this.configuredProperties.containsKey(key)) {
			return this.configuredProperties.getProperty(key);
		}

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

	public String getFitbitSchedule() {
		return fitbitSchedule;
	}

	public void setFitbitSchedule(String fitbitSchedule) {
		this.fitbitSchedule = fitbitSchedule;
	}

	public Boolean getFitbitSync() {
		return fitbitSync;
	}

	public Boolean isFitbitSync() {
		return fitbitSync;
	}

	public void setFitbitSync(Boolean fitbitSync) {
		this.fitbitSync = fitbitSync;
	}

	/**
	 * Loads the properties file into the bean properties of this class.
	 * @param properties
	 * @param name used for logging only, if a property can't load you will know what file it was from
	 */
	private void bindProperties(Properties properties, String name) {
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			try {
				BeanUtils.setProperty(this, (String) entry.getKey(), entry.getValue());
			} catch (Exception e) {
				LOGGER.warn("unable to load {} property:{}", name, entry.getKey(), e);
			}
		}
	}
}
