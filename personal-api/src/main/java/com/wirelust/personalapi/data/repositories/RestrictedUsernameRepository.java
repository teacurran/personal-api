package com.wirelust.personalapi.data.repositories;

import com.wirelust.personalapi.data.model.RestrictedUsername;
import com.wirelust.personalapi.util.StringUtils;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Date: 21-May-2015
 *
 * @author T. Curran
 */
@Repository
public abstract class RestrictedUsernameRepository extends AbstractEntityRepository<RestrictedUsername, Integer> {

	public abstract RestrictedUsername findAnyByUsernameNormalized(final String usermane);

	public boolean isRestricted(final String username) {
		RestrictedUsername restrictedUsername = findAnyByUsernameNormalized(StringUtils.normalizeUsername(username));
		return restrictedUsername != null;
	}
}
