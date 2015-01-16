package com.wirelust.personalapi.client.fitbit.representations;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 1/15/15
 *
 * @Author T. Curran
 */
@JsonRootName("user")
public class UserType implements Serializable {
	String aboutMe;

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
}
