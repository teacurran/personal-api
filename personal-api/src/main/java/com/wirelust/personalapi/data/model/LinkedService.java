package com.wirelust.personalapi.data.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Date: 11-03-2015
 *
 * @Author T. Curran
 */
@Entity
@Access( AccessType.FIELD )
@Cacheable
@NamedQueries({
		@NamedQuery(name = LinkedService.QUERY_TYPE_SERVICEID,
			query = "SELECT L FROM LinkedService L " +
					"WHERE L.type=:type " +
					"AND serviceId=:serviceId")
	}
)
public class LinkedService implements Serializable {

	public static final String TYPE_fitbit = "fitbit";

	public static final String QUERY_TYPE_SERVICEID = "LinkedService.getByTypeServiceId";

	private static final long serialVersionUID = 7600335339317594680L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne
	protected Account account;

	@Basic
	protected String type;

	@Basic
	protected String serviceId;

	@Basic
	protected String oauthToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLogin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLinked;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFriendsDownloaded;

	public LinkedService() {
		dateCreated = new Date();
		dateModified = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateLinked() {
		return dateLinked;
	}

	public void setDateLinked(Date dateLinked) {
		this.dateLinked = dateLinked;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String foreignId) {
		this.serviceId = foreignId;
	}

	public String getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
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

	public Date getDateFriendsDownloaded() {
		return dateFriendsDownloaded;
	}

	public void setDateFriendsDownloaded(Date dateFriendsDownloaded) {
		this.dateFriendsDownloaded = dateFriendsDownloaded;
	}
}