package com.wirelust.personalapi.client.fitbit.representations;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Date: 1/21/15
 *
 * @author T. Curran
 */
public class FoodPlanType {

	String intensity;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date estimatedDate;
	Boolean personalized;

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	public Date getEstimatedDate() {
		return estimatedDate;
	}

	public void setEstimatedDate(Date estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public Boolean getPersonalized() {
		return personalized;
	}

	public void setPersonalized(Boolean personalized) {
		this.personalized = personalized;
	}
}
