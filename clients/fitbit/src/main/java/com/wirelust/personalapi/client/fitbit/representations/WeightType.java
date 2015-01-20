package com.wirelust.personalapi.client.fitbit.representations;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class WeightType {

	Double bmi;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date date;
	Long logId;
	String time;
	Double weight;

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public Date getDate() {
		return (date == null) ? null : new Date(date.getTime());
	}

	public void setDate(Date date) {
		this.date = (date == null) ? null : new Date(date.getTime());
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
