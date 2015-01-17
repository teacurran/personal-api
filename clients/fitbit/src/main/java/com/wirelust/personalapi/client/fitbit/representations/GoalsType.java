package com.wirelust.personalapi.client.fitbit.representations;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 1/17/15
 *
 * @Author T. Curran
 */
@JsonRootName("goals")
public class GoalsType {
	Double weight;

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
