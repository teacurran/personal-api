package com.wirelust.personalapi.services;

import java.util.ResourceBundle;
import javax.inject.Inject;

import com.wirelust.personalapi.qualifiers.Localization;

/**
 * Date: 14-03-2015
 *
 * @Author T. Curran
 */
public class Messages {

	@Inject
	@Localization
	transient protected ResourceBundle locale;

	/**
	 * @param key
	 * @param defaultValue
	 * @param params
	 * @return
	 */
	public String getMessage(
			final String key,
			final String defaultValue,
			final Object... params) {

		if (locale.containsKey(key)) {
			return locale.getString(key);
		}
		return defaultValue;
	}

	public String getMessage(
			final String key) {

		return this.getMessage(key, null);
	}

}
