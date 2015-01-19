package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * 1/18/15
 *
 * @Author T. Curran
 */
public class ActivitySummaryType {

	Integer activityCalories;
	Integer caloriesBMR;
	Integer caloriesOut;
	List<DistanceType> distances;
	Double elevation;
	Integer fairlyActiveMinutes;
	Integer floors;
	Integer lightlyActiveMinutes;
	Integer marginalCalories;
	Integer sedentaryMinutes;
	Integer steps;
	Integer veryActiveMinutes;

	public Integer getActivityCalories() {
		return activityCalories;
	}

	public void setActivityCalories(Integer activityCalories) {
		this.activityCalories = activityCalories;
	}

	public Integer getCaloriesBMR() {
		return caloriesBMR;
	}

	public void setCaloriesBMR(Integer caloriesBMR) {
		this.caloriesBMR = caloriesBMR;
	}

	public Integer getCaloriesOut() {
		return caloriesOut;
	}

	public void setCaloriesOut(Integer caloriesOut) {
		this.caloriesOut = caloriesOut;
	}

	public List<DistanceType> getDistances() {
		return distances;
	}

	public void setDistances(List<DistanceType> distances) {
		this.distances = distances;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public Integer getFairlyActiveMinutes() {
		return fairlyActiveMinutes;
	}

	public void setFairlyActiveMinutes(Integer fairlyActiveMinutes) {
		this.fairlyActiveMinutes = fairlyActiveMinutes;
	}

	public Integer getFloors() {
		return floors;
	}

	public void setFloors(Integer floors) {
		this.floors = floors;
	}

	public Integer getLightlyActiveMinutes() {
		return lightlyActiveMinutes;
	}

	public void setLightlyActiveMinutes(Integer lightlyActiveMinutes) {
		this.lightlyActiveMinutes = lightlyActiveMinutes;
	}

	public Integer getMarginalCalories() {
		return marginalCalories;
	}

	public void setMarginalCalories(Integer marginalCalories) {
		this.marginalCalories = marginalCalories;
	}

	public Integer getSedentaryMinutes() {
		return sedentaryMinutes;
	}

	public void setSedentaryMinutes(Integer sedentaryMinutes) {
		this.sedentaryMinutes = sedentaryMinutes;
	}

	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}

	public Integer getVeryActiveMinutes() {
		return veryActiveMinutes;
	}

	public void setVeryActiveMinutes(Integer veryActiveMinutes) {
		this.veryActiveMinutes = veryActiveMinutes;
	}
}
