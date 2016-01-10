package com.wirelust.personalapi.client.googlefit.representations;

/**
 * Date: 10-Jan-2016
 *
 * @author T. Curran
 *
 * "device": {
 *    "uid": string,
 *    "type": string,
 *    "version": string,
 *    "model": string,
 *    "manufacturer": string
 *  },
 *
 */
public class Device {
	String uid;
	String type;
	String version;
	String model;
	String manufacturer;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
}
