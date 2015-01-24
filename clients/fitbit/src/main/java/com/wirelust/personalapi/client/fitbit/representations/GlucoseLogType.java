package com.wirelust.personalapi.client.fitbit.representations;

/**
 * 1/23/15
 *
 * @Author T. Curran
 */
public class GlucoseLogType {

	Double glucose;
	String tracker;
	String time;

	public Double getGlucose() {
		return glucose;
	}

	public void setGlucose(Double glucose) {
		this.glucose = glucose;
	}

	public String getTracker() {
		return tracker;
	}

	public void setTracker(String tracker) {
		this.tracker = tracker;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
