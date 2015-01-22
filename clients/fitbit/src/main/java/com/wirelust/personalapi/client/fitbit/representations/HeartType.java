package com.wirelust.personalapi.client.fitbit.representations;

/**
 * Date: 1/22/15
 *
 * @author T. Curran
 */
public class HeartType {

	Long logId;
	Integer heartRate;
	String tracker;
	String time;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Integer getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
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
