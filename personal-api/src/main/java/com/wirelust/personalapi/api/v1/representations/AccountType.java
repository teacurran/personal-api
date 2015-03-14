package com.wirelust.personalapi.api.v1.representations;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Date: 13-03-2015
 *
 * @Author T. Curran
 */
@XmlRootElement(name="account")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountType {

	protected Long id;

	protected String username;

	protected String realName;

	protected String location;

	protected String bio;

	protected String email;

	protected String password;

	protected String background;

	protected String avatar;

	protected Integer timezone;

	protected Date dateCreated;

	protected Date dateModified;

	protected Date dateLogin;

	protected String website;

	protected Integer followingCount;

	protected Integer followersCount;

	protected Integer publicVideoCount;

	protected Integer totalVideoCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateLogin() {
		return dateLogin;
	}

	public void setDateLogin(Date dateLogin) {
		this.dateLogin = dateLogin;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}

	public Integer getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	public Integer getPublicVideoCount() {
		return publicVideoCount;
	}

	public void setPublicVideoCount(Integer publicVideoCount) {
		this.publicVideoCount = publicVideoCount;
	}

	public Integer getTotalVideoCount() {
		return totalVideoCount;
	}

	public void setTotalVideoCount(Integer totalVideoCount) {
		this.totalVideoCount = totalVideoCount;
	}
}
