package com.wirelust.personalapi.services;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.approachingpi.services.mail.MailService;
import com.approachingpi.util.PasswordHash;
import com.wirelust.personalapi.data.model.Account;
import com.wirelust.personalapi.data.model.AccountPasswordReset;
import com.wirelust.personalapi.exceptions.ServiceException;
import com.wirelust.personalapi.util.StringUtils;

/**
 * Date: 7/12/12
 *
 * @author T. Curran
 */
@Named
public class AccountService implements Serializable {

	private static final long serialVersionUID = -4899150565335105759L;

	@Inject
	private MailService mailService;

	@Inject
	private Configuration configuration;

	@Inject
	transient EntityManager em;

	@Inject
	Messages messages;

	public Account find(final String inAccountId) {

		try {
			Long accountId = Long.parseLong(inAccountId);

			return em.find(Account.class, accountId);
		} catch (NumberFormatException nfe) {
			// do nothing, perhaps they passed in a username;
		}

		String usernameNormalized = StringUtils.normalizeUsername(inAccountId);
		TypedQuery<Account> query = em.createNamedQuery(Account.QUERY_BY_USERNAME_NORMALIZED, Account.class);
		query.setParameter("username", usernameNormalized);

		List<Account> results = query.getResultList();
		if (results != null && results.size() > 0) {
			return results.get(0);
		}

		return null;
	}

	public void setPassword(Account inAccount, String inPassword) throws ServiceException {
		if (inAccount == null) {
			return;
		}

		if (inPassword == null || inPassword.length() == 0) {
			throw new IllegalArgumentException("inPassword cannot be empty");
		}

		try {
			PasswordHash ph = new PasswordHash(inPassword);
			inAccount.setPasswordSalt(ph.getSalt());
			inAccount.setPassword(ph.getHash());

		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		} catch (InvalidKeySpecException e) {
			throw new ServiceException(e);
		}
	}

	public void requestPasswordReset(Account inAccount) {

		if (inAccount == null) {
			return;
		}
		if (StringUtils.isEmpty(inAccount.getEmail())) {
			return;
		}

		AccountPasswordReset passwordReset = new AccountPasswordReset(inAccount);
		em.persist(passwordReset);

		String resetPasswordUrl = configuration.getSetting("email.resetpassword.url");
		resetPasswordUrl = resetPasswordUrl.replaceAll("#KEY#", passwordReset.getUuid());

		mailService.setTemplate("reset-password.html");
		mailService.setReplacement("#NAME#", inAccount.getFullName());
		mailService.setReplacement("#RESET_URL#", resetPasswordUrl);

		mailService.sendEmail(
				inAccount.getFullName(),
				inAccount.getEmail(),
				messages.getMessage("email.passwordreset.subject", "Shortvid - password reset"));
	}

	public boolean checkPassword(Account inAccount, String inPassword) throws ServiceException {
		if (inAccount == null || inPassword == null) {
			return false;
		}

		try {
			return PasswordHash.check(inPassword, inAccount.getPasswordSalt(), inAccount.getPassword());
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		} catch (InvalidKeySpecException e) {
			throw new ServiceException(e);
		}
	}

}


