package com.wirelust.personalapi.data.model;

import java.util.Date;
import java.util.UUID;
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
import org.hibernate.annotations.Index;

/**
 * Date: 11-03-2015
 *
 * @Author T. Curran
 */
@Entity
@Access( AccessType.FIELD )
@Cacheable
@NamedQueries({
		@NamedQuery(name = Authorization.QUERY_BY_UUID,
				query = "SELECT S " +
						"FROM Authorization S " +
						"WHERE S.uuid = :uuid"),

		@NamedQuery(name = Authorization.QUERY_BY_ACCOUNT,
				query = "SELECT S " +
						"FROM Authorization S " +
						"WHERE S.account = :account"),

		@NamedQuery(name = Authorization.QUERY_BY_APP_ACCOUNT,
				query = "SELECT S " +
						"FROM Authorization S " +
						"WHERE S.apiApplicaiton = :apiApplication " +
						"AND S.account = :account"),

		@NamedQuery(name = Authorization.QUERY_BY_TOKEN,
				query = "SELECT S " +
						"FROM Authorization S " +
						"WHERE S.token = :token"),

		@NamedQuery(name = Authorization.QUERY_BY_REFRESH_TOKEN,
				query = "SELECT S " +
						"FROM Authorization S " +
						"WHERE S.refreshToken = :refreshToken")
	}
)
public class Authorization {

	public static final String QUERY_BY_UUID				= "Authorization.getByUuid";
	public static final String QUERY_BY_ACCOUNT				= "Authorization.getByAccount";
	public static final String QUERY_BY_APP_ACCOUNT			= "Authorization.getByAppAccount";
	public static final String QUERY_BY_TOKEN				= "Authorization.getByToken";
	public static final String QUERY_BY_REFRESH_TOKEN		= "Authorization.getByRefreshToken";

	@Id
	@Column(unique = true, nullable = false, insertable = true, updatable = true, length = 45)
	@GeneratedValue(generator="db-uuid")
	@GenericGenerator(name="db-uuid", strategy = "uuid")
	protected String uuid;

	@Basic
	@Index(name="authorization_requestcode")
	protected String requestCode;

	@Basic
	@Index(name="authorization_token")
	protected String token;

	@Basic
	@Index(name="authorization_refreshtoken")
	protected String refreshToken;

	@ManyToOne
	protected Account account;

	@ManyToOne
	protected ApiApplication apiApplicaiton;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateAccessed;

	public Authorization() {
		this.token = UUID.randomUUID().toString();
		this.refreshToken = UUID.randomUUID().toString();
		this.dateCreated = new Date();
		this.dateAccessed = new Date();
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

	public ApiApplication getApiApplicaiton() {
		return apiApplicaiton;
	}

	public void setApiApplicaiton(ApiApplication apiApplicaiton) {
		this.apiApplicaiton = apiApplicaiton;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateAccessed() {
		return dateAccessed;
	}

	public void setDateAccessed(Date dateAccessed) {
		this.dateAccessed = dateAccessed;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
}
