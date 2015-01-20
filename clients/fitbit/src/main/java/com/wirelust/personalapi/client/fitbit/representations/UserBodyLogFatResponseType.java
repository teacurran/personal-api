package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class UserBodyLogFatResponseType {

	List<FatType> fat;

	public List<FatType> getFat() {
		return fat;
	}

	public void setFat(List<FatType> fat) {
		this.fat = fat;
	}
}
