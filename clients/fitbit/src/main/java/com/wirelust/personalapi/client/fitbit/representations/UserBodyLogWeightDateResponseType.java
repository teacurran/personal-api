package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class UserBodyLogWeightDateResponseType {

	List<WeightType> weight;

	public List<WeightType> getWeight() {
		return weight;
	}

	public void setWeight(List<WeightType> weight) {
		this.weight = weight;
	}
}
