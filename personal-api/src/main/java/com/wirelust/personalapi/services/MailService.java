package com.wirelust.personalapi.services;

import java.io.Serializable;
import javax.inject.Named;

/**
 * Date: 6/23/11
 *
 * @author T. Curran
 */
@Named
public class MailService implements Serializable {

	private static final long serialVersionUID = 3572183154201772538L;

	public void setTemplate(
			final String template) {

	}

	public void setTextTemplate(
			final String template) {

	}

	public void setReplacement(
			final String key,
			String value) {

	}

	public void sendEmail(
			final String toName,
			final String toEmail,
			final String subject) {

	}

	public void sendEmail(
			final String to,
			final String subject) {

	}


}
