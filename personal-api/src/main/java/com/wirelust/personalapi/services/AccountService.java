package com.wirelust.personalapi.services;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

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

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
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
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException(e);
		}
	}

}


