package com.wirelust.personalapi.api.v1.resources;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wirelust.personalapi.api.v1.representations.AccountType;
import com.wirelust.personalapi.api.v1.representations.AuthType;
import com.wirelust.personalapi.data.model.Authorization;
import com.wirelust.personalapi.api.exceptions.ApplicationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 13-03-2015
 *
 * @Author T. Curran
 */
@Path("/account")
@Named
public class AccountResource {

	/* PASSWORD_BYPASS_STRING is used in the case that we need to call register service without passing in a valid password.
	It will get past validation but not set the password to the user's account.
	This is a UX edge case because of how we are implementing various oAuth providers.
	 */
	public static final String PASSWORD_BYPASS_STRING = "8fkjd6jXG392knd927ur98oijljKHjhjj66kjhkSDSWEXF";

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountResource.class);

	/**
	 * [ restricted, working ]
	 * <p/>
	 * Registers a new account with the site.
	 *
	 * @param inAccessCode restricted API access code
	 * @param inClientId   application id
	 * @param inUsername   the requested username
	 * @param inEmail      the user's email address.
	 * @param inPassword   a password that conforms to the password policy
	 * @param inFullName   the user's full name, or what ever they want displayed as their full name
	 * @return AuthType
	 */
	@Path("/register")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public AuthType register(
			@NotNull
			@FormParam("accessCode")
			final String inAccessCode,

			@NotNull
			@FormParam("client_id")
			final String inClientId,

			@NotNull
			@Size(min = 3, max = 20)
			@Pattern(regexp = "^[A-Za-z0-9_]+$")
			@FormParam("username")
			final String inUsername,

			@NotNull
			@Email
			@FormParam("email")
			final String inEmail,

			@NotNull
			@Size(min = 5, max = 20)
			@Pattern(regexp = "^[A-Za-z0-9_\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\|\\~\\`\\+\\=\\[\\]\\{\\}\\|\\\\]+$")
			@FormParam("password")
			final String inPassword,

			@NotNull
			@Size(min = 5)
			@FormParam("fullName")
			final String inFullName,

			@FormParam("inviteCode")
			final String inInviteCode
	) {

		LOGGER.debug("register()");

		String testingAccessCode = configuration.getSetting("accesscode.testing", "");
		if (!testingAccessCode.equals(inAccessCode)
				&& !sessionService.getAccessCode().equals(inAccessCode)) {
			throw new ApplicationException(EnumErrorCode.ACCESS_CODE_INVALID);
		}

		ApiApplication apiApplication = em.find(ApiApplication.class, inClientId);
		if (apiApplication == null) {
			throw new ApplicationException(EnumErrorCode.CLIENT_ID_INVALID);
		}

		// don't allow all numeric usernames
		if (inUsername.matches("^[0-9]+$")) {
			throw new ApplicationException(EnumErrorCode.USERNAME_INVALID);
		}

		// Make sure the username isn't taken
		String usernameNormalized = StringUtils.normalizeUsername(inUsername);
		TypedQuery<Account> usernameCheckQuery = em.createNamedQuery(Account.QUERY_BY_USERNAME_NORMALIZED, Account.class);
		usernameCheckQuery.setParameter("username", usernameNormalized);
		List<Account> usernameCheckResults = usernameCheckQuery.getResultList();
		if (usernameCheckResults != null && usernameCheckResults.size() > 0) {
			throw new ApplicationException(EnumErrorCode.USERNAME_EXISTS);
		}

		// Make sure the username isn't restricted
		TypedQuery<RestrictedUsername> restrictedCheckQuery = em.createNamedQuery(RestrictedUsername.QUERY_BY_USERNAME_NORMALIZED, RestrictedUsername.class);
		restrictedCheckQuery.setParameter("username", usernameNormalized);
		List<RestrictedUsername> restrictedCheckResults = restrictedCheckQuery.getResultList();
		if (restrictedCheckResults != null && restrictedCheckResults.size() > 0) {
			throw new ApplicationException(EnumErrorCode.USERNAME_EXISTS, "username exists", null);
		}

		// Check for an account by email address to make sure it doesn't already exist
		TypedQuery<Account> emailQuery = em.createNamedQuery(Account.QUERY_BY_EMAIL, Account.class);
		emailQuery.setParameter("email", inUsername);
		List<Account> emailResults = emailQuery.getResultList();
		if (emailResults != null && emailResults.size() > 0) {
			throw new ApplicationException(EnumErrorCode.EMAIL_EXISTS);
		}

		Account account = new Account();
		account.setUsername(inUsername);
		account.setUsernameNormalized(usernameNormalized);
		account.setEmail(inEmail);
		account.setFullName(inFullName);

		if (!inPassword.equals(AccountResource.PASSWORD_BYPASS_STRING)) {
			accountService.setPassword(account, inPassword);
		}

		// save the account
		em.persist(account);

		// create a new login session for this app/user
		Authorization authorization = authorizationService.getNewSession(null, account);

		AuthType authType = new AuthType();
		authType.setToken(authorization.getToken());
		authType.setCreated(authorization.getDateCreated());
		authType.setAccount(AccountHelper.toRepresentation(account, true));

		// bind the user to this session
		sessionService.setAccount(account);

		return authType;
	}

	/**
	 * [ working ] Checks to see if a username currently exists or not.
	 * Returns 204 on success, error if account exists.
	 *
	 * @param inUsername username
	 */
	@Path("/checkUsername")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void checkUsername(
			@NotNull
			@Size(min = 3, max = 20)
			@Pattern(regexp = "^[A-Za-z0-9_]+$")
			@FormParam("username")
			final String inUsername) {

		// Normalize the username
		String usernameNormalized = StringUtils.normalizeUsername(inUsername);

		TypedQuery<Account> usernameCheckQuery = em.createNamedQuery(Account.QUERY_BY_USERNAME_NORMALIZED, Account.class);
		usernameCheckQuery.setParameter("username", usernameNormalized);
		List<Account> usernameCheckResults = usernameCheckQuery.getResultList();
		if (usernameCheckResults != null && usernameCheckResults.size() > 0) {
			throw new ApplicationException(EnumErrorCode.USERNAME_EXISTS, "username exists", null);
		}

		TypedQuery<RestrictedUsername> restrictedCheckQuery = em.createNamedQuery(RestrictedUsername.QUERY_BY_USERNAME_NORMALIZED, RestrictedUsername.class);
		restrictedCheckQuery.setParameter("username", usernameNormalized);
		List<RestrictedUsername> restrictedCheckResults = restrictedCheckQuery.getResultList();
		if (restrictedCheckResults != null && restrictedCheckResults.size() > 0) {
			// we're throwing username_exists because we don't want the end user to know that this username is restricted.
			throw new ApplicationException(EnumErrorCode.USERNAME_EXISTS, "username exists", null);
		}

		// don't allow all numeric usernames
		if (inUsername.matches("^[0-9]+$")) {
			// TODO: determine if we need to do anything special here for the front end to tell the user the exact problem.
			throw new ApplicationException(EnumErrorCode.USERNAME_INVALID);
		}

	}


	/**
	 * [ restricted ]
	 *
	 * @param inAccessCode access code
	 * @param inClientId   application id
	 * @param inUsername   Username or email address of account
	 * @param inPassword   password
	 * @return AuthType
	 */
	@Path("/login")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public AuthType login(
			@NotNull
			@FormParam("accessCode")
			final String inAccessCode,

			@NotNull
			@FormParam("client_id")
			final String inClientId,

			@NotNull
			@FormParam("username")
			final String inUsername,

			@NotNull
			@FormParam("password")
			final String inPassword) {

		// TODO: allow the client to pass in ApiApplication.secret as a client_id
		// it will also have to check that the registered application has permissions to
		// behave like this.  This will enable our client app and others we approve
		// to use restricted methods with just their secret.
		// on second thought, we might want to add a different "secret" type string to
		// api_application meant for code that is going to be distributed and at risk
		// of being decompiled.
		if (!configuration.getSetting("accesscode.testing", "").equals(inAccessCode)
				&& !sessionService.getAccessCode().equals(inAccessCode)) {
			throw new ApplicationException(EnumErrorCode.ACCESS_CODE_INVALID);
		}

		ApiApplication apiApplication = em.find(ApiApplication.class, inClientId);
		if (apiApplication == null) {
			throw new ApplicationException(EnumErrorCode.CLIENT_ID_INVALID);
		}

		Account account = null;

		// look for an account with this username
		TypedQuery<Account> usernameQuery = em.createNamedQuery(Account.QUERY_BY_USERNAME_NORMALIZED, Account.class);
		usernameQuery.setParameter("username", StringUtils.normalizeUsername(inUsername));

		List<Account> usernameResults = usernameQuery.getResultList();
		if (usernameResults != null && usernameResults.size() > 0) {
			account = usernameResults.get(0);
		} else {
			// If we didn't find them based on the username, check by email
			TypedQuery<Account> emailQuery = em.createNamedQuery(Account.QUERY_BY_EMAIL, Account.class);
			emailQuery.setParameter("email", inUsername);

			List<Account> emailResults = emailQuery.getResultList();
			if (emailResults != null && emailResults.size() > 0) {
				account = emailResults.get(0);
			} else {
				throw new ApplicationException(EnumErrorCode.ACCOUNT_NOT_FOUND);
			}
		}

		if (!accountService.checkPassword(account, inPassword)) {
			throw new ApplicationException(EnumErrorCode.ACCOUNT_NOT_FOUND);
		}


		// create a new login session for this app/user
		Authorization authorization = authorizationService.getNewSession(apiApplication, account);

		AuthType authType = new AuthType();
		authType.setToken(authorization.getToken());
		authType.setCreated(authorization.getDateCreated());
		authType.setAccount(AccountHelper.toRepresentation(account, true));

		// bind the user to this session
		sessionService.setAccount(account);

		return authType;
	}

	/**
	 * [ working ]
	 *
	 * @param inOauthToken OAuth token
	 */
	@Path("/logout")
	@POST
	public void logout(
			@NotNull
			@FormParam("oauth_token")
			String inOauthToken) {

	}

	/**
	 *
	 * @param inOauthToken oauth_token
	 * @param inFullName   user's full name
	 * @param inLocation   user's location
	 * @param inBio        user's bio
	 * @param inEmail      email address
	 * @param inPassword   password
	 * @param inBackground URL of background image for the site (may be removed later)
	 * @param inTimezone   timezone
	 */
	@Path("/update")
	@POST
	public void update(
			@NotNull
			@FormParam("oauth_token")
			String inOauthToken,

			@NotNull
			@FormParam("fullName")
			String inFullName,

			@FormParam("location")
			String inLocation,

			@FormParam("bio")
			String inBio,

			@Email
			@FormParam("email")
			String inEmail,

			@Size(min = 5, max = 20)
			@Pattern(regexp = "^[A-Za-z0-9_\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\|\\~\\`\\+\\=\\[\\]\\{\\}\\|\\\\]+$")
			@FormParam("password")
			String inPassword,

			@FormParam("background")
			String inBackground,

			@FormParam("timezone")
			Integer inTimezone) {

	}

	/**
	 * Updates a single attribute of an account
	 *
	 * @param inOauthToken oauth_token
	 * @param inField      name of field to be updated
	 * @param inValue      value to be updated
	 */
	@Path("/updatesingle")
	@POST
	public void updateSingle(
			@NotNull
			@FormParam("client_id")
			final String inClientId,

			@NotNull
			@FormParam("oauth_token")
			String inOauthToken,

			@FormParam("field") String inField,

			@FormParam("value") String inValue) {

		// TODO: Implement

	}

	/**
	 * [ working ] Gets information about an account. if account specified is same as logged in account, more information will be provided.
	 * <p/>
	 * accountId can be either the numberic id or a username. if 'self' is passed as account id it will return data for the user of the oauth_token.
	 *
	 * @param inOauthToken oauth_token
	 * @param inAccountId  id of account to get info about
	 * @return accountType
	 */
	@Path("/{accountId}/info")
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public AccountType info(
			@NotNull
			@QueryParam("oauth_token")
			final String inOauthToken,

			@NotNull
			@PathParam("accountId")
			final String inAccountId
	) {

	}

	/**
	 * [ working ] Gets a list of accounts the current account is following
	 *
	 * @param inOauthToken oauth_token
	 * @param inAccountId  id of account to get info about
	 * @param inMax        max
	 * @param inOffset     offset
	 * @return accountListType
	 */
	@Path("/{accountId}/following")
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public AccountListType following(
			@NotNull
			@QueryParam("oauth_token")
			final String inOauthToken,

			@NotNull
			@PathParam("accountId")
			final String inAccountId,

			@QueryParam("max") Integer inMax,
			@QueryParam("offset") Integer inOffset
	) {

	}

	/**
	 * [ working ] Gets a list of accounts following the currnet account.
	 *
	 * @param inOauthToken oauth_token
	 * @param inAccountId  id of account to get info about
	 * @param inMax
	 * @param inOffset
	 * @return accountListType
	 */
	@Path("/{accountId}/followers")
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public AccountListType followers(
			@NotNull
			@QueryParam("oauth_token")
			final String inOauthToken,

			@NotNull
			@PathParam("accountId")
			final String inAccountId,

			@QueryParam("max") Integer inMax,
			@QueryParam("offset") Integer inOffset
	) {

	}

	/**
	 * [ working ] Follows an account
	 *
	 * @param inOauthToken oauth_token
	 * @param inAccountId  User to Follow
	 */
	@Path("/follow")
	@POST
	public void follow(
			@NotNull
			@FormParam("oauth_token")
			String inOauthToken,

			@NotNull
			@FormParam("accountId")
			Long inAccountId) {


	}

	/**
	 * [ working ] Unfollows an account
	 *
	 * @param inOauthToken oauth_token
	 * @param inAccountId  Account to unfollow
	 */
	@Path("/unfollow")
	@POST
	public void unfollow(
			@NotNull
			@FormParam("oauth_token")
			String inOauthToken,

			@NotNull
			@FormParam("accountId")
			Long inAccountId) {

		Authorization auth = authorizationService.getAuthorization(inOauthToken);
		if (auth == null) {
			throw new ApplicationException(EnumErrorCode.SESSION_INVALID);
		}

		Account authAccount = auth.getAccount();
		if (authAccount == null) {
			// this should never happen
			throw new ApplicationException(EnumErrorCode.SESSION_INVALID);
		}


		Account target = em.find(Account.class, inAccountId);
		if (target == null) {
			throw new ApplicationException(EnumErrorCode.ACCOUNT_NOT_FOUND);
		}

		if (authAccount.getFollowing().contains(target)) {
			authAccount.getFollowing().remove(target);
			em.merge(authAccount);

			accountDao.updateTotals(authAccount);
			accountDao.updateTotals(target);
		}
	}


}
