package com.wirelust.personalapi.data.model;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Date: 9/15/12
 *
 * @author T. Curran
 */
@Entity
@Access( AccessType.FIELD )
@Cacheable
@NamedQueries({
		@NamedQuery(name = AccountPasswordReset.QUERY_BY_ID,
				query = "SELECT A FROM AccountPasswordReset A " +
						"WHERE A.uuid = :id"),

		@NamedQuery(name = AccountPasswordReset.QUERY_BY_ACCOUNT,
				query = "SELECT A FROM AccountPasswordReset A " +
						"WHERE A.account = :account")
	}
)
public class AccountPasswordReset {

	public static final String QUERY_BY_ID					= "accountPasswordReset.byId";
	public static final String QUERY_BY_ACCOUNT				= "accountPasswordReset.byAccount";

	@Id
	@Column(unique = true, nullable = false, insertable = true, updatable = true, length = 45)
	@GeneratedValue(generator="db-uuid")
	@GenericGenerator(name="db-uuid", strategy = "uuid")
	String uuid;

	@ManyToOne
	protected Account account;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateCreated;

	public AccountPasswordReset(Account account) {
		this.account = account;
		this.dateCreated = new Date();
	}

	public AccountPasswordReset() {
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
