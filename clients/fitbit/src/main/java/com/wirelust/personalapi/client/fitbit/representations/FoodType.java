package com.wirelust.personalapi.client.fitbit.representations;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class FoodType {

	Boolean isFavorite;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date logDate;
	Long logId;
	LoggedFoodType loggedFood;
	NutritionalValueType nutritionalValues;

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public LoggedFoodType getLoggedFood() {
		return loggedFood;
	}

	public void setLoggedFood(LoggedFoodType loggedFood) {
		this.loggedFood = loggedFood;
	}

	public NutritionalValueType getNutritionalValues() {
		return nutritionalValues;
	}

	public void setNutritionalValues(NutritionalValueType nutritionalValues) {
		this.nutritionalValues = nutritionalValues;
	}
}
