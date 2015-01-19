package com.wirelust.personalapi.client.fitbit.representations;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class FoodSummaryType {

	Integer calories;
	Double carbs;
	Double fat;
	Double fiber;
	Double protein;
	Double sodium;
	Double water;

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Double getCarbs() {
		return carbs;
	}

	public void setCarbs(Double carbs) {
		this.carbs = carbs;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Double getFiber() {
		return fiber;
	}

	public void setFiber(Double fiber) {
		this.fiber = fiber;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	public Double getSodium() {
		return sodium;
	}

	public void setSodium(Double sodium) {
		this.sodium = sodium;
	}

	public Double getWater() {
		return water;
	}

	public void setWater(Double water) {
		this.water = water;
	}
}
