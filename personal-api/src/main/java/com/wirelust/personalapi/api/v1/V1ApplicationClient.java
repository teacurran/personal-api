package com.wirelust.personalapi.api.v1;

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
import javax.ws.rs.core.Response;

import com.wirelust.personalapi.api.v1.representations.AccountType;
import com.wirelust.personalapi.api.v1.representations.AuthType;
import com.wirelust.personalapi.util.PAConstants;
import org.hibernate.validator.constraints.Email;

/**
 * Date: 04-Apr-2015
 *
 * @author T. Curran
 */
public interface V1ApplicationClient {

	@POST
	@Path("/api/v1/accounts/register")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response register(
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
			final String inInviteCode);

	@Path("/api/v1/accounts/{accountId}")
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response info(
			@NotNull
			@QueryParam("oauth_token")
			final String inOauthToken,

			@NotNull
			@PathParam("accountId")
			final String inAccountId);

	@Path("/api/v1/accounts/checkUsername")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response checkUsername(
		@NotNull
		@Size(min = 3, max = 20)
		@Pattern(regexp = PAConstants.USERNAME_PATTERN)
		@FormParam("username")
		final String inUsername);

	@Path("/api/v1/accounts/login")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response login(
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
			final String inPassword);

	@Path("/api/v1/accounts/logout")
	@POST
	public Response logout(
			@NotNull
			@FormParam("oauth_token")
			String inOauthToken);
}
