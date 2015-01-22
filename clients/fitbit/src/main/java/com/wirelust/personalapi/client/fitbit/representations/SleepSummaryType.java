package com.wirelust.personalapi.client.fitbit.representations;

/**
 * Date: 1/22/15
 *
 * @author T. Curran
 */
public class SleepSummaryType {

	Integer totalMinutesAsleep;
	Integer totalSleepRecords;
	Integer totalTimeInBed;

	public Integer getTotalMinutesAsleep() {
		return totalMinutesAsleep;
	}

	public void setTotalMinutesAsleep(Integer totalMinutesAsleep) {
		this.totalMinutesAsleep = totalMinutesAsleep;
	}

	public Integer getTotalSleepRecords() {
		return totalSleepRecords;
	}

	public void setTotalSleepRecords(Integer totalSleepRecords) {
		this.totalSleepRecords = totalSleepRecords;
	}

	public Integer getTotalTimeInBed() {
		return totalTimeInBed;
	}

	public void setTotalTimeInBed(Integer totalTimeInBed) {
		this.totalTimeInBed = totalTimeInBed;
	}
}
