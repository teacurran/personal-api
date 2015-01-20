package com.wirelust.personalapi.client.fitbit.representations;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 1/17/15
 *
 * @Author T. Curran
 */
public class BodyGoalType {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date startDate;
	Double startWeight;
	Double weight;
	Double fat;

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Date getStartDate() {
		return (startDate == null) ? null : new Date(startDate.getTime());
	}

	public void setStartDate(Date startDate) {
		this.startDate = (startDate == null) ? null : new Date(startDate.getTime());
	}

	public Double getStartWeight() {
		return startWeight;
	}

	public void setStartWeight(Double startWeight) {
		this.startWeight = startWeight;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}
}
