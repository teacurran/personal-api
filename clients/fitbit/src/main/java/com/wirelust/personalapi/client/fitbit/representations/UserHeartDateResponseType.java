package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * Date: 1/22/15
 *
 * @author T. Curran
 */
public class UserHeartDateResponseType {

	List<HeartType> average;
	List<HeartType> heart;

	public List<HeartType> getAverage() {
		return average;
	}

	public void setAverage(List<HeartType> average) {
		this.average = average;
	}

	public List<HeartType> getHeart() {
		return heart;
	}

	public void setHeart(List<HeartType> heart) {
		this.heart = heart;
	}
}
