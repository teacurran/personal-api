package com.wirelust.personalapi.client.fitbit.representations;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 1/15/15
 *
 * @Author T. Curran
 */
@JsonRootName("user")
public class UserType implements Serializable {

	String aboutMe;
	String avatar;
	String avatar150;
	String city;
	String country;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date dateOfBirth;
	String displayName;
	String distanceUnit;
	String encodedId;
	String fullName;
	String gender;
	String glucoseUnit;
	Double height;
	String heightUnit;
	String nickname;
	String locale;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date memberSince;
	Long offsetFromUTCMillis;
	String startDayOfWeek;
	String state;
	Integer strideLengthRunning;
	Integer strideLengthWalking;
	String timezone;
	String waterUnit;
	Double weight;
	String weightUnit;

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar150() {
		return avatar150;
	}

	public void setAvatar150(String avatar150) {
		this.avatar150 = avatar150;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateOfBirth() {
		return (dateOfBirth == null) ? null : new Date(dateOfBirth.getTime());
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = (dateOfBirth == null) ? null : new Date(dateOfBirth.getTime());
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	public String getEncodedId() {
		return encodedId;
	}

	public void setEncodedId(String encodedId) {
		this.encodedId = encodedId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGlucoseUnit() {
		return glucoseUnit;
	}

	public void setGlucoseUnit(String glucoseUnit) {
		this.glucoseUnit = glucoseUnit;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getHeightUnit() {
		return heightUnit;
	}

	public void setHeightUnit(String heightUnit) {
		this.heightUnit = heightUnit;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Date getMemberSince() {
		return (memberSince == null) ? null : new Date(memberSince.getTime());
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = (memberSince == null) ? null : new Date(memberSince.getTime());;;
	}

	public Long getOffsetFromUTCMillis() {
		return offsetFromUTCMillis;
	}

	public void setOffsetFromUTCMillis(Long offsetFromUTCMillis) {
		this.offsetFromUTCMillis = offsetFromUTCMillis;
	}

	public String getStartDayOfWeek() {
		return startDayOfWeek;
	}

	public void setStartDayOfWeek(String startDayOfWeek) {
		this.startDayOfWeek = startDayOfWeek;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getStrideLengthRunning() {
		return strideLengthRunning;
	}

	public void setStrideLengthRunning(Integer strideLengthRunning) {
		this.strideLengthRunning = strideLengthRunning;
	}

	public Integer getStrideLengthWalking() {
		return strideLengthWalking;
	}

	public void setStrideLengthWalking(Integer strideLengthWalking) {
		this.strideLengthWalking = strideLengthWalking;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getWaterUnit() {
		return waterUnit;
	}

	public void setWaterUnit(String waterUnit) {
		this.waterUnit = waterUnit;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
}
