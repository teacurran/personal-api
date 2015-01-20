package com.wirelust.personalapi.client.fitbit.representations;

/**
 * 1/17/15
 *
 * @Author T. Curran
 */
public class ActivityGoalsType {

	Integer caloriesOut;
	Double distance;
	Integer floors;
	Integer steps;

	public Integer getCaloriesOut() {
		return caloriesOut;
	}

	public void setCaloriesOut(Integer caloriesOut) {
		this.caloriesOut = caloriesOut;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getFloors() {
		return floors;
	}

	public void setFloors(Integer floors) {
		this.floors = floors;
	}

	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}
}
