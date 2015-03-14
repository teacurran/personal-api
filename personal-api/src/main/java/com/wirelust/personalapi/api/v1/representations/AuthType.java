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
@XmlRootElement(name="auth")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthType {

	protected String token;

	protected Date created;

	protected Date expires;

	protected AccountType account;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AccountType getAccount() {
		return account;
	}

	public void setAccount(AccountType account) {
		this.account = account;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
