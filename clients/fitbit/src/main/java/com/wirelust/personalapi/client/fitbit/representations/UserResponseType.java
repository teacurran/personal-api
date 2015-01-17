package com.wirelust.personalapi.client.fitbit.representations;

import java.io.Serializable;

/**
 * 1/15/15
 *
 * @Author T. Curran
 */
public class UserResponseType implements Serializable {
	UserType user;

	public UserType getUser() {
		return user;
	}

	public void setUser(UserType user) {
		this.user = user;
	}
}
