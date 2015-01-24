package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 1/23/15
 *
 * @Author T. Curran
 */
public class GlucoseType {

	@JsonProperty("glucose")
	List<GlucoseLogType> log;

	Double hba1c;

	public List<GlucoseLogType> getLog() {
		return log;
	}

	public void setLog(List<GlucoseLogType> log) {
		this.log = log;
	}

	public Double getHba1c() {
		return hba1c;
	}

	public void setHba1c(Double hba1c) {
		this.hba1c = hba1c;
	}
}
