package com.wirelust.personalapi.api.v1;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
			@Pattern(regexp = "^[A-Za-z0-9_]+$")
			@FormParam("username")
			final String inUsername,

			@NotNull
			//@Email
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
			final String inInviteCode);
}
