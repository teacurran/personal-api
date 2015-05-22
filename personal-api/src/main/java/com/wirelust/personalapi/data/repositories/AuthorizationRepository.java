package com.wirelust.personalapi.data.repositories;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.wirelust.personalapi.data.model.Account;
import com.wirelust.personalapi.data.model.ApiApplication;
import com.wirelust.personalapi.data.model.Authorization;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 * Date: 14-03-2015
 *
 * @author T. Curran
 */
@Repository
public abstract class AuthorizationRepository extends AbstractEntityRepository<Authorization, String> {

	public Authorization getNewSession(
			final ApiApplication inApiApplication,
			final Account inAccount) {

		Authorization authorization = findExistingAuthorization(inApiApplication, inAccount);

		if (authorization == null) {
			authorization = new Authorization();
			authorization.setApiApplicaiton(inApiApplication);
			authorization.setAccount(inAccount);
			this.save(authorization);
		}

		return authorization;
	}

	@Query(named = Authorization.QUERY_BY_APP_ACCOUNT, singleResult = SingleResultType.ANY)
	public abstract Authorization findAnyByApiApplicationAccount(
			@QueryParam("apiApplication")
			final ApiApplication inApiApplication,

			@QueryParam("account")
			final Account inAccount);

	public Authorization findExistingAuthorization(
			final ApiApplication inApiApplication,
			final Account inAccount) {

		Authorization authorization = findAnyByApiApplicationAccount(inApiApplication, inAccount);
		if (authorization != null) {
			authorization.setDateAccessed(new Date());
			this.save(authorization);
			return authorization;
		}

		return null;
	}

	public abstract Authorization findAnyByToken(final String inToken);

	public abstract Authorization findAnyByRefreshToken(final String inRefreshToken);

}