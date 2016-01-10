package com.wirelust.personalapi.client.googlefit.representations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Date: 10-Jan-2016
 *
 * @author T. Curran
 */
public class DataSourceList {

	@JsonProperty("dataSource")
	List<DataSource> dataSources;

	public List<DataSource> getDataSources() {
		return dataSources;
	}

	public void setDataSources(List<DataSource> dataSources) {
		this.dataSources = dataSources;
	}
}
