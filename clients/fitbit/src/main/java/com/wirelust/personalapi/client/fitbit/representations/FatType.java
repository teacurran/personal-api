package com.wirelust.personalapi.client.fitbit.representations;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class FatType {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date date;
	Double fat;
	Long logId;
	String time;

	public Date getDate() {
		return (date == null) ? null : new Date(date.getTime());
	}

	public void setDate(Date date) {
		this.date = (date == null) ? null : new Date(date.getTime());
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
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
}
