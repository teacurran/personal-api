package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class LoggedFoodType {

	public static final String ACCESS_TYPE_PUBLIC = "PUBLIC";
	public static final String ACCESS_TYPE_PRIVATE = "PRIVATE";
	public static final String ACCESS_TYPE_SHARED = "SHARED";

	String accessLevel;
	Double amount;
	String brand;
	Integer calories;
	Long foodId;
	Integer mealTypeId;
	String locale;
	String name;
	UnitType unit;
	List<Long> units;

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

	public Integer getMealTypeId() {
		return mealTypeId;
	}

	public void setMealTypeId(Integer mealTypeId) {
		this.mealTypeId = mealTypeId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UnitType getUnit() {
		return unit;
	}

	public void setUnit(UnitType unit) {
		this.unit = unit;
	}

	public List<Long> getUnits() {
		return units;
	}

	public void setUnits(List<Long> units) {
		this.units = units;
	}
}
