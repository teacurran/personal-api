package com.wirelust.personalapi.locales;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageContextConfig;
import org.apache.deltaspike.core.api.message.MessageTemplate;
import org.apache.deltaspike.jsf.impl.message.JsfMessageResolver;

/**
 * Date: 31-Mar-2015
 *
 * @author T. Curran
 */
@MessageBundle
@MessageContextConfig(messageResolver = JsfMessageResolver.class)
public interface I18n {

	@MessageTemplate("{error.unexpected}")
	String unexpectedError(String message);

}
