package com.wirelust.personalapi.client.googlefit.representations;

/**
 * Date: 10-Sep-2015
 *
 * @author T. Curran
 *
 * {
 *  "dataStreamId": string,
 *  "name": string,
 *  "dataStreamName": string,
 *  "type": string,
 *  "dataType": {
 *    "name": string,
 *    "field": [
 *      {
 *        "name": string,
 *        "format": string
 *      }
 *    ]
 *  },
 *  "device": {
 *    "uid": string,
 *    "type": string,
 *    "version": string,
 *    "model": string,
 *    "manufacturer": string
 *  },
 *  "application": {
 *    "packageName": string,
 *    "version": string,
 *    "detailsUrl": string,
 *    "name": string
 *  }
 *}
 *
 */
public class DataSource {
	String dataStreamId;
	String name;
	String dataStreamName;
	String type;
	DataType dataType;
	Device device;
	Application application;

	public String getDataStreamId() {
		return dataStreamId;
	}

	public void setDataStreamId(String dataStreamId) {
		this.dataStreamId = dataStreamId;
	}

	public String getDataStreamName() {
		return dataStreamName;
	}

	public void setDataStreamName(String dataStreamName) {
		this.dataStreamName = dataStreamName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
