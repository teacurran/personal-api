package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class UserWaterLogDateResponseType {

	WaterSummaryType summary;
	List<WaterType> water;

	public List<WaterType> getWater() {
		return water;
	}

	public void setWater(List<WaterType> water) {
		this.water = water;
	}

	public WaterSummaryType getSummary() {
		return summary;
	}

	public void setSummary(WaterSummaryType summary) {
		this.summary = summary;
	}
}
