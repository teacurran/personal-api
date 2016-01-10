package com.wirelust.personalapi.client.googlefit.representations;

/**
 * Date: 10-Jan-2016
 *
 * @author T. Curran
 *
 *    "field": [
 *      {
 *        "name": string,
 *        "format": string
 *      }
 *    ]
 *
 */
public class Field {

	String name;
	String format;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
