package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class UserFoodLogDateResponseType {

	List<FoodType> foods;
	FoodSummaryType summary;
	FoodGoalsType goals;

	public List<FoodType> getFoods() {
		return foods;
	}

	public void setFoods(List<FoodType> foods) {
		this.foods = foods;
	}

	public FoodSummaryType getSummary() {
		return summary;
	}

	public void setSummary(FoodSummaryType summary) {
		this.summary = summary;
	}

	public FoodGoalsType getGoals() {
		return goals;
	}

	public void setGoals(FoodGoalsType goals) {
		this.goals = goals;
	}
}
