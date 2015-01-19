package com.wirelust.personalapi.client.fitbit.representations;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 1/17/15
 *
 * @Author T. Curran
 */
public class FoodGoalsType {

	Integer calories;

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}
}
