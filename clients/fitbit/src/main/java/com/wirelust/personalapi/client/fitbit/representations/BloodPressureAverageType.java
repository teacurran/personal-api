package com.wirelust.personalapi.client.fitbit.representations;

/**
 * Date: 1/23/15
 *
 * @author T. Curran
 */
public class BloodPressureAverageType {

	String condition;
	Integer diastolic;
	Integer systolic;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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
}
