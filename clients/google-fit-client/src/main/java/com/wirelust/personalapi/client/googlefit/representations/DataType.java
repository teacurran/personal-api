package com.wirelust.personalapi.client.googlefit.representations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Date: 10-Jan-2016
 *
 * @author T. Curran
 *
 *   "dataType": {
 *    "name": string,
 *    "field": [
 *      {
 *        "name": string,
 *        "format": string
 *      }
 *    ]
 *  },
 *
 */
public class DataType {

	String name;

	@JsonProperty("field")
	List<Field> fields;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
