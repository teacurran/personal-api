package com.wirelust.personalapi.client.fitbit.representations;

import java.util.List;

/**
 * Date: 1/22/15
 *
 * @author T. Curran
 */
public class UserSleepDateResponseType {

	SleepSummaryType summary;
	List<SleepType> sleep;

	public SleepSummaryType getSummary() {
		return summary;
	}

	public void setSummary(SleepSummaryType summary) {
		this.summary = summary;
	}

	public List<SleepType> getSleep() {
		return sleep;
	}

	public void setSleep(List<SleepType> sleep) {
		this.sleep = sleep;
	}
}
