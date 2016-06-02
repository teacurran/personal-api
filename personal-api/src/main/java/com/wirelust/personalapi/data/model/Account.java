package com.wirelust.personalapi.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wirelust.personalapi.util.StringUtils;

/**
 * Date: 11-03-2015
 *
 * @Author T. Curran
 */
@Entity
@Cacheable
@NamedQueries({
		@NamedQuery(name = Account.QUERY_ALL,
				query = "SELECT A " +
						"FROM Account A"),

		@NamedQuery(name = Account.QUERY_BY_ID,
				query = "SELECT A " +
						"FROM Account A " +
						"WHERE A.id = :id"),

		@NamedQuery(name = Account.QUERY_BY_USERNAME,
				query = "SELECT A " +
						"FROM Account A " +
						"WHERE A.username = :username"),

		@NamedQuery(name = Account.QUERY_BY_USERNAME_NORMALIZED,
				query = "SELECT A " +
						"FROM Account A " +
						"WHERE A.usernameNormalized = :username"),

		@NamedQuery(name = Account.QUERY_BY_USERNAME_NORMALIZED_OR_EMAIL,
				query = "SELECT A " +
						"FROM Account A " +
						"WHERE A.usernameNormalized = :username " +
						"OR A.email = :email"),

		@NamedQuery(name = Account.QUERY_BY_EMAIL,
				query = "SELECT A " +
						"FROM Account A " +
						"WHERE A.email = :email")
	}
)
public class Account implements java.io.Serializable {

	public static final String QUERY_ALL					= "account.getAll";
	public static final String QUERY_BY_ID					= "account.byId";
	public static final String QUERY_BY_EMAIL				= "account.byEmail";
	public static final String QUERY_BY_USERNAME			= "account.byUsername";
	public static final String QUERY_BY_USERNAME_NORMALIZED	= "account.byUsernameNormalized";
	public static final String QUERY_BY_USERNAME_NORMALIZED_OR_EMAIL	= "account.byUsernameNormalizedOrEmail";

	public static final String DISABLED_REASON_GENERIC			= "generic";
	public static final String DISABLED_REASON_EXCESS_PW_FAIL	= "passfail";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(unique = true)
	protected String username;

	@Column(unique = true)
	protected String usernameNormalized;

	@Column(length=200)
	protected String fullName;

	@Basic
	protected String location;

	@Lob
	protected String bio;

	@Basic
	protected String email;

	@Basic
	protected String password;

	@Basic
	protected String passwordSalt;

	@Basic
	protected String background;

	@Basic
	protected String avatar;

	@Basic
	protected Integer timezone;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateModified;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateLogin;

	@Basic
	private Integer loginFailureCount = 0;

	@Basic
	protected boolean disabled;

	@Basic
	protected boolean admin;

	@Basic
	protected String website;

	@Basic
	protected Integer followingCount;

	@Basic
	protected Integer followersCount;

	@Basic
	protected Integer publicVideoCount;

	@Basic
	protected Integer totalVideoCount;

	@Basic
	protected String disabledReason;

	protected ArrayList<LinkedService> linkedServices = new ArrayList<>(0);

	protected ArrayList<AccountSetting> settings = new ArrayList<>(0);

	public Account() {
		dateCreated = new Date();
		dateModified = new Date();
	}

	public LinkedService getLinkedService(final String inService) {
		if (linkedServices != null && linkedServices.size() > 0) {
			for (LinkedService ls : linkedServices) {
				if (inService.equals(ls.getType())) {
					return ls;
				}
			}
		}
		return null;
	}

	public void setUsername(String username) {
		this.username = username;
		this.setUsernameNormalized(StringUtils.normalizeUsername(username));
	}

	public String toString() {
		return getUsername();
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public List<LinkedService> getLinkedServices() {
		return linkedServices;
	}

	public void setLinkedServices(List<LinkedService> linkedServices) {
		this.linkedServices = new ArrayList<>(linkedServices);
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public List<AccountSetting> getSettings() {
		return settings;
	}

	public void setSettings(List<AccountSetting> settings) {
		this.settings = new ArrayList<>(settings);
	}

	public String getUsernameNormalized() {
		return usernameNormalized;
	}

	public void setUsernameNormalized(String usernameNormalized) {
		this.usernameNormalized = usernameNormalized;
	}

	public String getDisabledReason() {
		return disabledReason;
	}

	public void setDisabledReason(String disabledReason) {
		this.disabledReason = disabledReason;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public void setWebsite(String inWebsite) {
		website = inWebsite;
	}

	public String getWebsite() {
		return website;
	}

	public Integer getFollowingCount() {
		if (followingCount == null) {
			followingCount = 0;
		}
		return followingCount;
	}

	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}

	public Integer getFollowersCount() {
		if (followersCount == null) {
			followersCount = 0;
		}
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
		if (totalVideoCount == null) {
			totalVideoCount = 0;
		}
		return totalVideoCount;
	}

	public void setTotalVideoCount(Integer totalVideoCount) {
		this.totalVideoCount = totalVideoCount;
	}
}
