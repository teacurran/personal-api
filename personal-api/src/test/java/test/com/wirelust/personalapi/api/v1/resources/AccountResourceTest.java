package test.com.wirelust.personalapi.api.v1.resources;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.wirelust.personalapi.api.exceptions.ApiException;
import com.wirelust.personalapi.api.v1.representations.EnumErrorCode;
import com.wirelust.personalapi.api.v1.resources.AccountResource;
import com.wirelust.personalapi.data.model.Account;
import com.wirelust.personalapi.data.model.ApiApplication;
import com.wirelust.personalapi.data.repositories.AccountRepository;
import com.wirelust.personalapi.data.repositories.ApiApplicationRepository;
import com.wirelust.personalapi.data.repositories.AuthorizationRepository;
import com.wirelust.personalapi.data.repositories.RestrictedUsernameRepository;
import com.wirelust.personalapi.services.AccountService;
import com.wirelust.personalapi.services.Configuration;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Date: 13-Jul-2016
 *
 * @author T. Curran
 */
@RunWith(CdiRunner.class)
public class AccountResourceTest {

	@Inject
	AccountResource accountResource;

	@Produces
	@Mock
	ApiApplicationRepository apiApplicationRepository;

	@Produces
	@Mock
	AccountService accountService;

	@Produces
	@Mock
	AuthorizationRepository authorizationRepository;

	@Produces
	@Mock
	AccountRepository accountRepository;

	@Produces
	@Mock
	Configuration configuration;

	@Produces
	@Mock
	RestrictedUsernameRepository restrictedUsernameRepository;

	@Test
	public void shouldThrowExceptionWhenRegisterGetsInvalidClientId() {
		try {
			accountResource.register("clientId", null, null, null, null, null);
			Assert.fail();
		} catch (ApiException e) {
			assertEquals(EnumErrorCode.CLIENT_ID_INVALID, e.getErrorCode());
		}
	}

	@Test
	public void shouldThrowExceptionWhenRegisterGetsInvalidUsername() {

		when(apiApplicationRepository.findBy(any(String.class))).thenReturn(new ApiApplication());
		when(accountRepository.usernameIsValid(any(String.class))).thenReturn(false);

		try {
			accountResource.register("clientId", "username", null, null, null, null);
			Assert.fail();
		} catch (ApiException e) {
			assertEquals(EnumErrorCode.USERNAME_INVALID, e.getErrorCode());
		}
	}

	@Test
	public void shouldThrowExceptionWhenRegisterGetsExistingUsername() {

		when(apiApplicationRepository.findBy(any(String.class))).thenReturn(new ApiApplication());
		when(accountRepository.usernameIsValid(any(String.class))).thenReturn(true);
		when(accountRepository.usernameExists(any(String.class))).thenReturn(true);

		try {
			accountResource.register("clientId", "username", null, null, null, null);
			Assert.fail();
		} catch (ApiException e) {
			assertEquals(EnumErrorCode.USERNAME_EXISTS, e.getErrorCode());
		}
	}

	@Test
	public void shouldThrowExceptionWhenRegisterGetsRestrictedUsername() {

		when(apiApplicationRepository.findBy(any(String.class))).thenReturn(new ApiApplication());
		when(accountRepository.usernameIsValid(any(String.class))).thenReturn(true);
		when(accountRepository.usernameExists(any(String.class))).thenReturn(false);
		when(restrictedUsernameRepository.isRestricted(any(String.class))).thenReturn(true);

		try {
			accountResource.register("clientId", "username", null, null, null, null);
			Assert.fail();
		} catch (ApiException e) {
			assertEquals(EnumErrorCode.USERNAME_EXISTS, e.getErrorCode());
		}
	}

	@Test
	public void shouldThrowExceptionWhenRegisterGetsDuplicateEmail() {

		when(apiApplicationRepository.findBy(any(String.class))).thenReturn(new ApiApplication());
		when(accountRepository.usernameIsValid(any(String.class))).thenReturn(true);
		when(accountRepository.usernameExists(any(String.class))).thenReturn(false);
		when(restrictedUsernameRepository.isRestricted(any(String.class))).thenReturn(false);
		when(accountRepository.findAnyByEmail(any(String.class))).thenReturn(new Account());


		try {
			accountResource.register("clientId", "username", null, null, null, null);
			Assert.fail();
		} catch (ApiException e) {
			assertEquals(EnumErrorCode.EMAIL_EXISTS, e.getErrorCode());
		}
	}

}
