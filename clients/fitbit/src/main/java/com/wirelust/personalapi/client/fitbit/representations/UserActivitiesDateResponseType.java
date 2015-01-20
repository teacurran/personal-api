package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * 1/18/15
 *
 * @Author T. Curran
 */
public class UserActivitiesDateResponseType {

	List<ActivityType> activities;
	ActivityGoalsType goals;
	ActivitySummaryType summary;

	public List<ActivityType> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityType> activities) {
		this.activities = activities;
	}

	public ActivityGoalsType getGoals() {
		return goals;
	}

	public void setGoals(ActivityGoalsType goals) {
		this.goals = goals;
	}

	public ActivitySummaryType getSummary() {
		return summary;
	}

	public void setSummary(ActivitySummaryType summary) {
		this.summary = summary;
	}
}
