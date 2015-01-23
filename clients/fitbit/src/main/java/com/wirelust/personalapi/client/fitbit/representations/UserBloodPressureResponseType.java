package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * Date: 1/23/15
 *
 * @author T. Curran
 */
public class UserBloodPressureResponseType {

	BloodPressureAverageType average;
	List<BloodPressureType> bp;

	public BloodPressureAverageType getAverage() {
		return average;
	}

	public void setAverage(BloodPressureAverageType average) {
		this.average = average;
	}

	public List<BloodPressureType> getBp() {
		return bp;
	}

	public void setBp(List<BloodPressureType> bp) {
		this.bp = bp;
	}
}
