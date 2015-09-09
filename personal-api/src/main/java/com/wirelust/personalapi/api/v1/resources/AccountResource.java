package com.wirelust.personalapi.api.v1.resources;

import com.wirelust.personalapi.api.exceptions.ApplicationException;
import com.wirelust.personalapi.api.v1.representations.AccountType;
import com.wirelust.personalapi.api.v1.representations.AuthType;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.data.model.Account;
import com.wirelust.personalapi.data.model.ApiApplication;
import com.wirelust.personalapi.data.model.Authorization;
import com.wirelust.personalapi.data.repositories.AccountRepository;
import com.wirelust.personalapi.data.repositories.ApiApplicationRepository;
import com.wirelust.personalapi.data.repositories.RestrictedUsernameRepository;
import com.wirelust.personalapi.helpers.AccountHelper;
import com.wirelust.personalapi.services.AccountService;
import com.wirelust.personalapi.data.repositories.AuthorizationRepository;
import com.wirelust.personalapi.services.Configuration;
import com.wirelust.personalapi.util.PAConstants;
import com.wirelust.personalapi.util.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
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

/**
 * Date: 13-03-2015
 *
 * @author T. Curran
 */
@Path("/accounts")
@Named
public class AccountResource {

	/* PASSWORD_BYPASS_STRING is used in the case that we need to call register service without passing in a valid
	password.  It will get past validation but not set the password to the user's account.
	This is a UX edge case because of how we are implementing various oAuth providers.
	 */
	public static final String PASSWORD_BYPASS_STRING = "8fkjd6jXG392knd927ur98oijljKHjhjj66kjhkSDSWEXF";

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountResource.class);

	@Inject
	Configuration configuration;

	@Inject
	AccountService accountService;

	@Inject
	AuthorizationRepository authorizationRepository;

	@Inject
	ApiApplicationRepository apiApplicationRepository;

	@Inject
	AccountRepository accountRepository;

	@Inject
	RestrictedUsernameRepository restrictedUsernameRepository;

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
			@Pattern(regexp = PAConstants.USERNAME_PATTERN)
			@FormParam("username")
			final String inUsername,

			@NotNull
			@Email
			@FormParam("email")
			final String inEmail,

			@NotNull
			@Size(min = 5, max = 20)
			@Pattern(regexp = PAConstants.ACCOUNT_PASSWORD_PATTERN)
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
		// TODO: re-implement access codes
		//if (!testingAccessCode.equals(inAccessCode)
		//		&& !sessionService.getAccessCode().equals(inAccessCode)) {
		//	throw new ApplicationException(EnumErrorCode.ACCESS_CODE_INVALID);
		//}

		ApiApplication apiApplication = apiApplicationRepository.findBy(inClientId);
		if (apiApplication == null) {
			throw new ApplicationException(EnumErrorCode.CLIENT_ID_INVALID);
		}

		// check to make sure the username is valid
		if (!accountRepository.usernameIsValid(inUsername)) {
			throw new ApplicationException(EnumErrorCode.USERNAME_INVALID);
		}

		// Make sure the username isn't taken
		if (accountRepository.usernameExists(inUsername)) {
			throw new ApplicationException(EnumErrorCode.USERNAME_EXISTS);
		}

		// Make sure the username isn't restricted
		if (restrictedUsernameRepository.isRestricted(inUsername)) {
			// we're throwing username_exists because we
			// don't want the end user to know that this username is restricted.
			throw new ApplicationException(EnumErrorCode.USERNAME_EXISTS, "username exists", null);
		}

		// Check for an account by email address to make sure it doesn't already exist
		if (accountRepository.findAnyByEmail(inEmail) != null) {
			throw new ApplicationException(EnumErrorCode.EMAIL_EXISTS);
		}

		Account account = new Account();
		account.setUsername(inUsername);
		account.setEmail(inEmail);
		account.setFullName(inFullName);

		if (!inPassword.equals(AccountResource.PASSWORD_BYPASS_STRING)) {
			accountService.setPassword(account, inPassword);
		}

		// save the account
		accountRepository.save(account);

		// create a new login session for this app/user
		Authorization authorization = authorizationRepository.getNewSession(null, account);

		AuthType authType = new AuthType();
		authType.setToken(authorization.getToken());
		authType.setCreated(authorization.getDateCreated());
		authType.setAccount(AccountHelper.toRepresentation(account, true));

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
			@Pattern(regexp = PAConstants.USERNAME_PATTERN)
			@FormParam("username")
			final String inUsername) {

		// Normalize the username
		String usernameNormalized = StringUtils.normalizeUsername(inUsername);

		if (accountRepository.usernameExists(inUsername)) {
			throw new ApplicationException(EnumErrorCode.USERNAME_EXISTS, "username exists", null);
		}

		if (restrictedUsernameRepository.isRestricted(inUsername)) {
			// we're throwing username_exists because we
			// don't want the end user to know that this username is restricted.
			throw new ApplicationException(EnumErrorCode.USERNAME_EXISTS, "username exists", null);
		}

		// check to make sure the username is valid
		if (!accountRepository.usernameIsValid(inUsername)) {
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

		ApiApplication apiApplication = apiApplicationRepository.findBy(inClientId);
		if (apiApplication == null) {
			throw new ApplicationException(EnumErrorCode.CLIENT_ID_INVALID);
		}

		Account account = accountRepository.findAnyByUsername(inUsername);

		if (account == null) {
			account = accountRepository.findAnyByEmail(inUsername);

			if (account == null) {
				throw new ApplicationException(EnumErrorCode.ACCOUNT_NOT_FOUND);
			}
		}

		if (!accountService.checkPassword(account, inPassword)) {
			throw new ApplicationException(EnumErrorCode.ACCOUNT_NOT_FOUND);
		}

		// create a new login session for this app/user
		Authorization authorization = authorizationRepository.getNewSession(apiApplication, account);

		AuthType authType = new AuthType();
		authType.setToken(authorization.getToken());
		authType.setCreated(authorization.getDateCreated());
		authType.setAccount(AccountHelper.toRepresentation(account, true));

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

		Authorization auth =  authorizationRepository.findAnyByToken(inOauthToken);
		if (auth == null) {
			throw new ApplicationException(EnumErrorCode.SESSION_INVALID);
		}

		Account account = auth.getAccount();
		if (account == null) {
			// this should never happen
			throw new ApplicationException(EnumErrorCode.SESSION_INVALID);
		}

		authorizationRepository.attachAndRemove(auth);
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

			@FormParam("email")
			String inEmail,

			@Size(min = 5, max = 20)
			@Pattern(regexp = PAConstants.ACCOUNT_PASSWORD_PATTERN)
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
	 * Gets information about an account. if account specified is same as logged in account,
	 * more information will be provided.
	 * <p/>
	 * accountId can be either the numberic id or a username. if 'self' is passed as account id it
	 * will return data for the user of the oauth_token.
	 *
	 * @param inOauthToken oauth_token
	 * @param inAccountId  id of account to get info about
	 * @return accountType
	 */
	@Path("/{accountId}")
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

		Authorization auth = authorizationRepository.findAnyByToken(inOauthToken);
		if (auth == null) {
			throw new ApplicationException(EnumErrorCode.SESSION_INVALID);
		}

		Account authAccount = auth.getAccount();
		if (authAccount == null) {
			// this should never happen
			throw new ApplicationException(EnumErrorCode.SESSION_INVALID);
		}

		Account account;
		if ("self".equalsIgnoreCase(inAccountId) || "me".equalsIgnoreCase(inAccountId)) {
			account = authAccount;
		} else {
			account = accountRepository.findAnyByAccountIdOrUsername(inAccountId);
			if (account == null) {
				throw new ApplicationException(EnumErrorCode.ACCOUNT_NOT_FOUND);
			}
		}

		AccountType at = AccountHelper.toRepresentation(account, true);

		if (account.getId().equals(authAccount.getId())) {
			at.setEmail(account.getEmail());
		}

		return at;
	}


}
