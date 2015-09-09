package com.wirelust.personalapi.client.googlefit.representations;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Date: 18-Jul-2015
 *
 * @author T. Curran
 */
public class NutritionSummary {

	Map<String, Double> nutrients;
	Integer meal_type;
	@JsonProperty("food_item")
	String foodItem;

	public Map<String, Double> getNutrients() {
		return nutrients;
	}

	public void setNutrients(Map<String, Double> nutrients) {
		this.nutrients = nutrients;
	}

	public Integer getMeal_type() {
		return meal_type;
	}

	public void setMeal_type(Integer meal_type) {
		this.meal_type = meal_type;
	}

	public String getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}
}

