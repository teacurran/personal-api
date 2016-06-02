package com.wirelust.personalapi.client.fitbit.representations;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class UserFoodLogGoalResponseType {

	FoodGoalsType goals;
	FoodPlanType foodPlan;

	public FoodGoalsType getGoals() {
		return goals;
	}

	public void setGoals(FoodGoalsType goals) {
		this.goals = goals;
	}

	public FoodPlanType getFoodPlan() {
		return foodPlan;
	}

	public void setFoodPlan(FoodPlanType foodPlan) {
		this.foodPlan = foodPlan;
	}
}
