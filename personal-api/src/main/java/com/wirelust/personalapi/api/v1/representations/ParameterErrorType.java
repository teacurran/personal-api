package com.wirelust.personalapi.api.v1.representations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Date: 28-03-2015
 *
 * @Author T. Curran
 */
@XmlRootElement(name="parameter-error")
@JsonRootName("parameter-error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParameterErrorType {

	String parameter;
	String message;

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
