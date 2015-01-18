package com.wirelust.personalapi.client.fitbit.representations;

/**
 * 1/18/15
 *
 * @Author T. Curran
 */
public class ActivityType {

	Long activityId;
	Long activityParentId;
	Integer calories;
	String description;
	Double distance;
	Long duration;
	Boolean hasStartTime;
	Boolean isFavorite;
	Long logId;
	String name;
	String startTime;
	Integer steps;

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getActivityParentId() {
		return activityParentId;
	}

	public void setActivityParentId(Long activityParentId) {
		this.activityParentId = activityParentId;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Boolean getHasStartTime() {
		return hasStartTime;
	}

	public void setHasStartTime(Boolean hasStartTime) {
		this.hasStartTime = hasStartTime;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}
}
