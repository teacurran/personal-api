package com.wirelust.personalapi.services;

/**
 * Date: 14-03-2015
 *
 * @Author T. Curran
 */
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.wirelust.personalapi.data.model.Account;
import com.wirelust.personalapi.data.model.ApiApplication;
import com.wirelust.personalapi.data.model.Authorization;

/**
 * Date: 7/21/12
 *
 * @author T. Curran
 */
@Named
public class AuthorizationService {

	@PersistenceContext
	protected EntityManager em;

	public Authorization getNewSession(
			final ApiApplication inApiApplication,
			final Account inAccount) {

		Authorization authorization = findExistingAuthorization(inApiApplication, inAccount);

		if (authorization == null) {
			authorization = new Authorization();
			authorization.setApiApplicaiton(inApiApplication);
			authorization.setAccount(inAccount);
			em.persist(authorization);
		}

		return authorization;
	}

	public Authorization findExistingAuthorization(
			final ApiApplication inApiApplication,
			final Account inAccount) {

		TypedQuery<Authorization> sessionQuery = em.createNamedQuery(Authorization.QUERY_BY_APP_ACCOUNT, Authorization.class);
		sessionQuery.setParameter("apiApplication", inApiApplication);
		sessionQuery.setParameter("account", inAccount);

		Authorization authorization = null;

		List<Authorization> authorizationResults = sessionQuery.getResultList();
		if (authorizationResults != null && authorizationResults.size() > 0) {
			authorization = authorizationResults.get(0);
			authorization.setDateAccessed(new Date());
			em.merge(authorization);
			return authorization;
		}

		return authorization;
	}

	public Authorization getAuthorization(final String inToken) {

		TypedQuery<Authorization> query = em.createNamedQuery(Authorization.QUERY_BY_TOKEN, Authorization.class);
		query.setParameter("token", inToken);

		List<Authorization> results = query.getResultList();
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	public Authorization getAuthorizationByRefresh(final String inRefreshToken) {

		TypedQuery<Authorization> query = em.createNamedQuery(Authorization.QUERY_BY_REFRESH_TOKEN, Authorization.class);
		query.setParameter("refreshToken", inRefreshToken);

		List<Authorization> results = query.getResultList();
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}
}