package com.wirelust.personalapi.client.googlefit.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Date: 18-Jul-2015
 *
 * @author T. Curran
 */
public class ActivitySummary {

	Integer activity;
	Integer duration;
	@JsonProperty("num_segments")
	Integer numSegments;

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getNumSegments() {
		return numSegments;
	}

	public void setNumSegments(Integer numSegments) {
		this.numSegments = numSegments;
	}
}
