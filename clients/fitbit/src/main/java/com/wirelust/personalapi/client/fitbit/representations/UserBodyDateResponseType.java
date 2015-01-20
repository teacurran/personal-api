package com.wirelust.personalapi.client.fitbit.representations;

/**
 * 1/17/15
 *
 * @Author T. Curran
 */
public class UserBodyDateResponseType {
	BodyType body;
	BodyGoalsType goals;

	public BodyType getBody() {
		return body;
	}

	public void setBody(BodyType body) {
		this.body = body;
	}

	public BodyGoalsType getGoals() {
		return goals;
	}

	public void setGoals(BodyGoalsType goals) {
		this.goals = goals;
	}
}
