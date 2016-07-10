package test.com.wirelust.personalapi.api.helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Date;

import com.wirelust.personalapi.api.helpers.AccountHelper;
import com.wirelust.personalapi.api.v1.representations.AccountType;
import com.wirelust.personalapi.data.model.Account;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Date: 13-Jul-2016
 *
 * @author T. Curran
 */
public class AccountHelperTest {

	Date dateCreated = new Date(new Date().getTime() - 10000);
	Date dateModified =  new Date(new Date().getTime() - 90000);
	Date dateLogin =  new Date(new Date().getTime() - 80000);

	/**
	 * This method simply instantiates a private constructor to ensure code coverage for it so the
	 * coverage reports aren't diminished
	 */
	@Test
	public void testConstantsConstructorIsPrivate() throws Exception {
		Constructor<AccountHelper> constructor = AccountHelper.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void shouldTransformAccountToAccountType() {
		Account account = new Account();
		account.setId(1234L);
		account.setAdmin(true);
		account.setUsername("username");
		account.setFullName("Full Name");
		account.setAvatar("avatar");
		account.setBackground("background");
		account.setBio("bio");
		account.setDateCreated(dateCreated);
		account.setDateModified(dateModified);
		account.setDateLogin(dateLogin);

		AccountType accountType = AccountHelper.toRepresentation(account);

		assertEquals(account.getId(), accountType.getId());
		assertEquals(account.getUsername(), accountType.getUsername());
		assertEquals(account.getFullName(), accountType.getRealName());
		assertEquals(account.getAvatar(), accountType.getAvatar());
	}

	@Test
	public void shouldTransformAccountToExtendedAccountType() {

		Account account = new Account();
		account.setId(1234L);
		account.setAdmin(true);
		account.setAvatar("avatar");
		account.setBackground("background");
		account.setBio("bio");
		account.setDateCreated(dateCreated);
		account.setDateModified(dateModified);
		account.setDateLogin(dateLogin);

		AccountType accountType = AccountHelper.toExtendedRepresentation(account);

		assertEquals(account.getAvatar(), accountType.getAvatar());
		assertEquals(account.getBackground(), accountType.getBackground());
		assertEquals(account.getBio(), accountType.getBio());
		assertEquals(dateCreated, accountType.getDateCreated());
		assertEquals(dateModified, accountType.getDateModified());
		assertEquals(dateLogin, account.getDateLogin());
	}
}
