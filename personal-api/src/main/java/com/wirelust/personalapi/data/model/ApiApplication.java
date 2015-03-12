package com.wirelust.personalapi.data.model;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Date: 11-03-2015
 *
 * @Author T. Curran
 */
@Entity
@Access( AccessType.FIELD )
@Cacheable
@NamedQueries({
		@NamedQuery(name = ApiApplication.QUERY_ALL,
				query = "SELECT A " +
						"FROM ApiApplication A"),

		@NamedQuery(name = ApiApplication.QUERY_BY_ID,
				query = "SELECT A " +
						"FROM ApiApplication A WHERE A.uuid = :uuid"),

		@NamedQuery(name = ApiApplication.QUERY_BY_ID_SECRET,
				query = "SELECT A " +
						"FROM ApiApplication A " +
						"WHERE A.uuid = :uuid " +
						"AND A.secret = :secret")
	}
)
public class ApiApplication {

	public static final String QUERY_ALL = "ApiApplication.getAll";
	public static final String QUERY_BY_ID = "ApiApplication.getById";
	public static final String QUERY_BY_ID_SECRET = "ApiApplication.getByIdSecret";

	@Id
	@Column(unique = true, nullable = false, insertable = true, updatable = true, length = 45)
	@GeneratedValue(generator="db-uuid")
	@GenericGenerator(name="db-uuid", strategy = "uuid")
	protected String uuid;

	@ManyToOne
	protected Account account;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateModified;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateApproved;

	@Basic
	protected Boolean approved;

	@Basic
	protected String name;

	@Basic
	protected String secret;

	@Basic
	protected String callbackUri;

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

	public Date getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(Date dateApproved) {
		this.dateApproved = dateApproved;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(String callbackUrl) {
		this.callbackUri = callbackUrl;
	}
}
