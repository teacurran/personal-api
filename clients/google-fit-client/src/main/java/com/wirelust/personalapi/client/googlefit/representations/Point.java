package com.wirelust.personalapi.client.googlefit.representations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Date: 25-Jan-2016
 *
 * @author T. Curran
 */
public class Point {

	String dataTypeName;
	Long endTimeNanos;
	String originDataSourceId;
	Long startTimeNanos;
	@JsonProperty("value")
	List<Value> values;

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public Long getEndTimeNanos() {
		return endTimeNanos;
	}

	public void setEndTimeNanos(Long endTimeNanos) {
		this.endTimeNanos = endTimeNanos;
	}

	public String getOriginDataSourceId() {
		return originDataSourceId;
	}

	public void setOriginDataSourceId(String originDataSourceId) {
		this.originDataSourceId = originDataSourceId;
	}

	public Long getStartTimeNanos() {
		return startTimeNanos;
	}

	public void setStartTimeNanos(Long startTimeNanos) {
		this.startTimeNanos = startTimeNanos;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}
}
