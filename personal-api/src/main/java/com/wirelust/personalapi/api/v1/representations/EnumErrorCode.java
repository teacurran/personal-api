package com.wirelust.personalapi.api.v1.representations;

import javax.ws.rs.core.Response;
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
	GENERIC_ERROR(1000, Response.Status.INTERNAL_SERVER_ERROR),

	@XmlEnumValue("1001")
	RESOURCE_NOT_FOUND(1001, Response.Status.NOT_FOUND),

	@XmlEnumValue("1002")
	REPRESENTATION_PARSE_ERROR(1002, Response.Status.BAD_REQUEST),

	@XmlEnumValue("1003")
	REPRESENTATION_FORMAT_ERROR(1003, Response.Status.BAD_REQUEST),

	@XmlEnumValue("1004")
	ILLEGAL_ARGUMENT_ERROR(1004, Response.Status.BAD_REQUEST),

	@XmlEnumValue("1005")
	ACCESS_CODE_INVALID(1005, Response.Status.BAD_REQUEST),

	@XmlEnumValue("1006")
	SESSION_INVALID(1006, Response.Status.UNAUTHORIZED),

	@XmlEnumValue("1007")
	SESSION_EXPIRED(1007, Response.Status.UNAUTHORIZED),

	@XmlEnumValue("1008")
	CLIENT_ID_INVALID(1008, Response.Status.UNAUTHORIZED),

	@XmlEnumValue("2000")
	OPERATION_ERROR(2000, Response.Status.BAD_REQUEST),

	@XmlEnumValue("2001")
	ACCOUNT_NOT_FOUND(2001, Response.Status.UNAUTHORIZED),

	@XmlEnumValue("2002")
	USERNAME_EXISTS(2002, Response.Status.BAD_REQUEST),

	@XmlEnumValue("2003")
	USERNAME_INVALID(2003, Response.Status.UNAUTHORIZED),

	@XmlEnumValue("2004")
	EMAIL_EXISTS(2004, Response.Status.BAD_REQUEST),

	@XmlEnumValue("2005")
	ACCOUNT_ATTEMPT_TO_FOLLOW_SELF(2005, Response.Status.BAD_REQUEST),

	@XmlEnumValue("2006")
	UNKNOWN_SERVICE_TYPE(2006, Response.Status.BAD_REQUEST),

	@XmlEnumValue("2007")
	SERVICE_ALREADY_LINKED(2007, Response.Status.BAD_REQUEST),

	@XmlEnumValue("2008")
	SERVICE_TOKEN_INVALID(2008, Response.Status.BAD_REQUEST),

	@XmlEnumValue("2009")
	OBJECT_NOT_FOUND(2009, Response.Status.NOT_FOUND),

	@XmlEnumValue("2010")
	PASSWORD_RESET_PREVIOUSLY(2010, Response.Status.BAD_REQUEST),

	@XmlEnumValue("2011")
	PASSWORD_RESET_KEY_NOT_FOUND(2011, Response.Status.BAD_REQUEST),

	@XmlEnumValue("5001")
	NOT_IMPLEMENTED(5001, Response.Status.INTERNAL_SERVER_ERROR);

	private final int value;
	private final Response.Status status;

	EnumErrorCode(
		final int v,
		Response.Status status) {

		this.value = v;
		this.status = status;
	}

	public int value() {

		return this.value;
	}

	public Response.Status responseStatus() {
		return this.status;
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
