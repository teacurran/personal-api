package com.wirelust.personalapi.client.fitbit.representations;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 1/17/15
 *
 * @Author T. Curran
 *
 *         "bicep":40,
         "bmi":16.14,
         "calf":11.2,
         "chest":50,
         "fat":0,
         "forearm":22.3,
         "hips":34,
         "neck":30,
         "thigh":45,
         "waist":60,
         "weight":80.55
 */
@JsonRootName("body")
public class BodyType implements Serializable {

	Double bicep;
	Double bmi;
	Double calf;
	Double chest;
	Double fat;
	Double forearm;
	Double hips;
	Double neck;
	Double thigh;
	Double waist;
	Double weight;

	public Double getBicep() {
		return bicep;
	}

	public void setBicep(Double bicep) {
		this.bicep = bicep;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public Double getCalf() {
		return calf;
	}

	public void setCalf(Double calf) {
		this.calf = calf;
	}

	public Double getChest() {
		return chest;
	}

	public void setChest(Double chest) {
		this.chest = chest;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Double getForearm() {
		return forearm;
	}

	public void setForearm(Double forearm) {
		this.forearm = forearm;
	}

	public Double getHips() {
		return hips;
	}

	public void setHips(Double hips) {
		this.hips = hips;
	}

	public Double getNeck() {
		return neck;
	}

	public void setNeck(Double neck) {
		this.neck = neck;
	}

	public Double getThigh() {
		return thigh;
	}

	public void setThigh(Double thigh) {
		this.thigh = thigh;
	}

	public Double getWaist() {
		return waist;
	}

	public void setWaist(Double waist) {
		this.waist = waist;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
