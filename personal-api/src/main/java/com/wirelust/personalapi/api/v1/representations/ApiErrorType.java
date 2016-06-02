package com.wirelust.personalapi.api.v1.representations;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Date: 28-03-2015
 *
 * @Author T. Curran
 */
@XmlType(name = "", propOrder = {"code", "text", "detail", "parameterErrors"})
@XmlRootElement(name = "error")
@JsonRootName("error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiErrorType {

	@XmlElement(required = true)
	protected EnumErrorCode code;

	@XmlElement(required = true)
	protected String text;

	@XmlElement
	protected String detail;

	@XmlElement(name = "parameter-error")
	private List<ParameterErrorType> parameterErrors = null;

	/**
	 * Gets the value of the errorCode property.
	 *
	 * @return possible object is {@link EnumErrorCode }
	 */
	public EnumErrorCode getCode() {

		if (this.code == null) {
			this.code = EnumErrorCode.GENERIC_ERROR;
		}

		return this.code;
	}

	/**
	 * Sets the value of the errorCode property.
	 *
	 * @param value allowed object is {@link EnumErrorCode }
	 */
	public void setCode(final EnumErrorCode value) {

		this.code = value;
	}

	/**
	 * Gets the value of the errorText property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getText() {

		return this.text;
	}

	/**
	 * Sets the value of the errorText property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setText(final String value) {

		this.text = value;
	}

	/**
	 * Gets the value of the errorMessage property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getDetail() {

		return this.detail;
	}

	/**
	 * Sets the value of the errorMessage property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setDetail(final String value) {

		this.detail = value;
	}

	public List<ParameterErrorType> getParameterErrors() {
		if (parameterErrors == null) {
			parameterErrors = new ArrayList<>();
		}
		return parameterErrors;
	}

	public void setParameterErrors(List<ParameterErrorType> parameterErrors) {
		this.parameterErrors = parameterErrors;
	}

	public void addParameterError(ParameterErrorType error) {
		if (parameterErrors == null) {
			parameterErrors = new ArrayList<>();
		}
		parameterErrors.add(error);
	}
}
