package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * 1/18/15
 *
 * @Author T. Curran
 */
public class UserActivitiesDateResponseType {

	List<ActivityType> activities;
	GoalsType goals;
	ActivitySummaryType summary;

	public List<ActivityType> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityType> activities) {
		this.activities = activities;
	}

	public GoalsType getGoals() {
		return goals;
	}

	public void setGoals(GoalsType goals) {
		this.goals = goals;
	}

	public ActivitySummaryType getSummary() {
		return summary;
	}

	public void setSummary(ActivitySummaryType summary) {
		this.summary = summary;
	}
}
