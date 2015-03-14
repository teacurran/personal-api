package com.wirelust.personalapi.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Access( AccessType.FIELD )
public class LoginAudit implements Serializable {

	@Id
	@Column(unique = true, nullable = false, insertable = true, updatable = true, length = 45)
	@GeneratedValue(generator="db-uuid")
	@GenericGenerator(name="db-uuid", strategy = "uuid")
	protected String uuid;

	@ManyToOne
	protected Account account;

	@Basic
	private String username;

	@Temporal( TemporalType.TIMESTAMP )
	@Column( length = 0 )
	private Date dateCreated;

	@Basic
	private Boolean isSuccessful;

	@Basic
	private String ipAddress;

	public LoginAudit() {

	}

	public LoginAudit(
		final Account account,
		final Boolean isSuccessful ) {

		this.account = account;
		if (account != null) {
			this.username = account.getUsername();
		}
		this.isSuccessful = isSuccessful;
		this.dateCreated = new Date();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Boolean getSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(Boolean successful) {
		isSuccessful = successful;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
