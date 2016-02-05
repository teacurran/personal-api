package com.wirelust.personalapi.client.googlefit.representations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Date: 25-Jan-2016
 *
 * @author T. Curran
 */
public class DataSet {

	String dataSourceId;
	Long maxEndTimeNs;
	Long minStartTimeNs;
	@JsonProperty("point")
	List<Point> points;

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public Long getMaxEndTimeNs() {
		return maxEndTimeNs;
	}

	public void setMaxEndTimeNs(Long maxEndTimeNs) {
		this.maxEndTimeNs = maxEndTimeNs;
	}

	public Long getMinStartTimeNs() {
		return minStartTimeNs;
	}

	public void setMinStartTimeNs(Long minStartTimeNs) {
		this.minStartTimeNs = minStartTimeNs;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
}
