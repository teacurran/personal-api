package com.wirelust.personalapi.client.googlefit.representations;

/**
 * Date: 10-Jan-2016
 *
 * @author T. Curran
 *
 *   "application": {
 *    "packageName": string,
 *    "version": string,
 *    "detailsUrl": string,
 *    "name": string
 *  }
 *
 */
public class Application {
	String packageName;
	String version;
	String detailsUrl;
	String name;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDetailsUrl() {
		return detailsUrl;
	}

	public void setDetailsUrl(String detailsUrl) {
		this.detailsUrl = detailsUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
