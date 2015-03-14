package com.wirelust.personalapi.api.v1.representations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Date: 13-03-2015
 *
 * @Author T. Curran
 * <p/>
 * Java class for enum-error-code.
 * <p/>
 * <p/>
 * The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <p/>
 * <pre>
 * &lt;simpleType name="enum-error-code">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *     &lt;enumeration value="1000"/>
 *     &lt;enumeration value="1001"/>
 *     &lt;enumeration value="1002"/>
 *     &lt;enumeration value="1003"/>
 *     &lt;enumeration value="2000"/>
 *     &lt;enumeration value="2001"/>
 *     &lt;enumeration value="2002"/>
 *     &lt;enumeration value="2003"/>
 *     &lt;enumeration value="2004"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "enum-error-code")
@XmlEnum(Integer.class)
public enum EnumErrorCode {

	@XmlEnumValue("1000")
	GENERIC_ERROR(1000),

	@XmlEnumValue("1001")
	RESOURCE_NOT_FOUND(1001),

	@XmlEnumValue("1002")
	REPRESENTATION_PARSE_ERROR(1002),

	@XmlEnumValue("1003")
	REPRESENTATION_FORMAT_ERROR(1003),

	@XmlEnumValue("1004")
	ILLEGAL_ARGUMENT_ERROR(1004),

	@XmlEnumValue("1005")
	ACCESS_CODE_INVALID(1005),

	@XmlEnumValue("1006")
	SESSION_INVALID(1006),

	@XmlEnumValue("1007")
	SESSION_EXPIRED(1007),

	@XmlEnumValue("1008")
	CLIENT_ID_INVALID(1008),

	@XmlEnumValue("2000")
	OPERATION_ERROR(2000),

	@XmlEnumValue("2001")
	ACCOUNT_NOT_FOUND(2001),

	@XmlEnumValue("2002")
	USERNAME_EXISTS(2002),

	@XmlEnumValue("2003")
	USERNAME_INVALID(2003),

	@XmlEnumValue("2004")
	EMAIL_EXISTS(2004),

	@XmlEnumValue("2005")
	ACCOUNT_ATTEMPT_TO_FOLLOW_SELF(2005),

	@XmlEnumValue("2006")
	UNKNOWN_SERVICE_TYPE(2006),

	@XmlEnumValue("2007")
	SERVICE_ALREADY_LINKED(2007),

	@XmlEnumValue("2008")
	SERVICE_TOKEN_INVALID(2008),

	@XmlEnumValue("2009")
	OBJECT_NOT_FOUND(2009),

	@XmlEnumValue("2010")
	PASSWORD_RESET_PREVIOUSLY(2010),

	@XmlEnumValue("2011")
	PASSWORD_RESET_KEY_NOT_FOUND(2011);


	private final int value;

	EnumErrorCode(
			final int v) {

		this.value = v;
	}

	public int value() {

		return this.value;
	}

	public static EnumErrorCode fromValue(
			final int v) {

		for (final EnumErrorCode c : EnumErrorCode.values()) {
			if (c.value == v) {
				return c;
			}
		}
		throw new IllegalArgumentException(String.valueOf(v));
	}

}