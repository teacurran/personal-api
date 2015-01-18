package com.wirelust.personalapi.client.fitbit.representations;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 1/17/15
 *
 * @Author T. Curran
 */
@JsonRootName("goals")
public class GoalsType {
	Double weight;
	Integer caloriesOut;
	Double distance;
	Integer floors;
	Integer steps;

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

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
