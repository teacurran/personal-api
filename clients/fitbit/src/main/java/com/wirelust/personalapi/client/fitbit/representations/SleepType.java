package com.wirelust.personalapi.client.fitbit.representations;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Date: 1/22/15
 *
 * @author T. Curran
 */
public class SleepType {

	Boolean isMainSleep;
	Long logId;
	Integer efficiency;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	Date startTime;
	Long duration;
	Integer minutesToFallAsleep;
	Integer minutesAsleep;
	Integer minutesAwake;
	Integer minutesAfterWakeup;
	Integer awakeCount;
	Long awakeDuration;
	Integer restlessCount;
	Long restlessDuration;
	Long timeInBed;
	List<SleepMinuteType> minuteData;
	private Integer awakeningsCount;

	public Boolean getIsMainSleep() {
		return isMainSleep;
	}

	public void setIsMainSleep(Boolean isMainSleep) {
		this.isMainSleep = isMainSleep;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Integer getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(Integer efficiency) {
		this.efficiency = efficiency;
	}

	public Date getStartTime() {
		return (startTime == null) ? null : new Date(startTime.getTime());
	}

	public void setStartTime(Date startTime) {
		this.startTime = (startTime == null) ? null : new Date(startTime.getTime());
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Integer getMinutesToFallAsleep() {
		return minutesToFallAsleep;
	}

	public void setMinutesToFallAsleep(Integer minutesToFallAsleep) {
		this.minutesToFallAsleep = minutesToFallAsleep;
	}

	public Integer getMinutesAsleep() {
		return minutesAsleep;
	}

	public void setMinutesAsleep(Integer minutesAsleep) {
		this.minutesAsleep = minutesAsleep;
	}

	public Integer getMinutesAwake() {
		return minutesAwake;
	}

	public void setMinutesAwake(Integer minutesAwake) {
		this.minutesAwake = minutesAwake;
	}

	public Integer getMinutesAfterWakeup() {
		return minutesAfterWakeup;
	}

	public void setMinutesAfterWakeup(Integer minutesAfterWakeup) {
		this.minutesAfterWakeup = minutesAfterWakeup;
	}

	/**
	 * @deprecated
	 * @return count of awakenings
	 */
	@Deprecated
	public Integer getAwakeningsCount() {
		return awakeningsCount;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void setAwakeningsCount(Integer awakeningsCount) {
		this.awakeningsCount = awakeningsCount;
	}

	public Integer getAwakeCount() {
		return awakeCount;
	}

	public void setAwakeCount(Integer awakeCount) {
		this.awakeCount = awakeCount;
	}

	public Long getAwakeDuration() {
		return awakeDuration;
	}

	public void setAwakeDuration(Long awakeDuration) {
		this.awakeDuration = awakeDuration;
	}

	public Integer getRestlessCount() {
		return restlessCount;
	}

	public void setRestlessCount(Integer restlessCount) {
		this.restlessCount = restlessCount;
	}

	public Long getRestlessDuration() {
		return restlessDuration;
	}

	public void setRestlessDuration(Long restlessDuration) {
		this.restlessDuration = restlessDuration;
	}

	public Long getTimeInBed() {
		return timeInBed;
	}

	public void setTimeInBed(Long timeInBed) {
		this.timeInBed = timeInBed;
	}

	public List<SleepMinuteType> getMinuteData() {
		return minuteData;
	}

	public void setMinuteData(List<SleepMinuteType> minuteData) {
		this.minuteData = minuteData;
	}
}
