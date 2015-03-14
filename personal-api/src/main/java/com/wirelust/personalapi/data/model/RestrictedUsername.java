package com.wirelust.personalapi.data.model;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.Basic;
import javax.persistence.TemporalType;

/**
 * Date: 14-03-2015
 *
 * @Author T. Curran
 */
@Entity
@Access( AccessType.FIELD )
@Cacheable
@NamedQueries({
		@NamedQuery(name = RestrictedUsername.QUERY_BY_USERNAME_NORMALIZED,
				query = "SELECT A FROM RestrictedUsername A " +
						"WHERE A.usernameNormalized = :username")
	}
)
public class RestrictedUsername {

	public static final String QUERY_BY_USERNAME_NORMALIZED	= "restrictedusername.byUsernameNormalized";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Basic
	String username;

	@Basic
	String usernameNormalized;

	@Basic
	String reason;

	@Temporal(TemporalType.TIMESTAMP)
	Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	Date dateModified;

	public RestrictedUsername() {
		this.dateCreated = new Date();
		this.dateModified = new Date();
	}

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

	public String getUsernameNormalized() {
		return usernameNormalized;
	}

	public void setUsernameNormalized(String usernameNormalized) {
		this.usernameNormalized = usernameNormalized;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
}