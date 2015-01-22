package com.wirelust.personalapi.client.fitbit.representations;

/**
 * Date: 1/22/15
 *
 * @author T. Curran
 */
public class SleepMinuteType {

	String dateTime;
	Long value;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
