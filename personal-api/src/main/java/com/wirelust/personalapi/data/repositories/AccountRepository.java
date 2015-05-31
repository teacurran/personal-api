package com.wirelust.personalapi.data.repositories;

import java.util.List;
import javax.persistence.TypedQuery;

import com.wirelust.personalapi.data.model.Account;
import com.wirelust.personalapi.util.PAConstants;
import com.wirelust.personalapi.util.StringUtils;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Date: 5/22/15
 *
 * @author T. Curran
 */
@Repository
public abstract class AccountRepository extends AbstractEntityRepository<Account, Long> {

	public abstract Account findAnyByEmail(final String inEmail);

	public abstract Account findAnyByUsernameNormalized(final String inUsername);

	public Account findAnyByAccountIdOrUsername(final String inAccountId) {

		Account account;
		try {
			// if the ID passed in is numeric, return it even if we don't find anything
			// there is no point in searching by username
			account = findBy(Long.parseLong(inAccountId));
			return account;
		} catch (NumberFormatException nfe) {
			// do nothing, perhaps they passed in a username;
		}

		account = findAnyByUsername(inAccountId);

		return account;
	}

	public Account findAnyByUsername(final String inUsername) {
		return findAnyByUsernameNormalized(StringUtils.normalizeUsername(inUsername));
	}

	public boolean usernameExists(final String inUsername) {
		Account restrictedUsername = findAnyByUsernameNormalized(StringUtils.normalizeUsername(inUsername));
		return (restrictedUsername != null);
	}

	public boolean usernameIsValid(final String inUsername) {

		if (inUsername == null) {
			return false;
		}

		if (!inUsername.matches(PAConstants.USERNAME_PATTERN)) {
			return false;
		}

		// make sure the username is not all numeric
		if (inUsername.matches("^[0-9]+$")) {
			return false;
		}
		return true;
	}
}
