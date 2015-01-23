package com.wirelust.personalapi.client.fitbit.representations;

/**
 * Date: 1/23/15
 *
 * @author T. Curran
 */
public class BloodPressureType {

	Long logId;
	Integer diastolic;
	Integer systolic;
	String time;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Integer getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(Integer diastolic) {
		this.diastolic = diastolic;
	}

	public Integer getSystolic() {
		return systolic;
	}

	public void setSystolic(Integer systolic) {
		this.systolic = systolic;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
