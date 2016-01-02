package com.wirelust.personalapi.util;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import org.apache.commons.codec.binary.Base64;

public class PasswordHash {
	// The higher the number of iterations the more
	// expensive computing the hash is for us
	// and also for a brute force attack.
	private static final int iterations = 10 * 1024;
	private static final int saltLen = 16;
	private static final int desiredKeyLen = 256;

	protected String salt;
	protected String hash;


	/**
	 * Computes a salted PBKDF2 hash of given plaintext password
	 * suitable for storing in a database.
	 */
	public PasswordHash(
			final String inPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {

		byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);

		// generate and store the hash
		this.hash = hash(inPassword, salt);

		// store the salt
		this.salt = Base64.encodeBase64String(salt);
	}

	public String getSalt() {
		return salt;
	}

	public String getHash() {
		return hash;
	}

	/**
	 * Checks whether given plaintext password corresponds
	 * to a stored salted hash of the password.
	 */
	public static boolean check(
			final String inPassword,
			final String inSalt,
			final String inHash) throws NoSuchAlgorithmException, InvalidKeySpecException {

		String hashOfInput = hash(inPassword, Base64.decodeBase64(inSalt));

		return hashOfInput.equals(inHash);
	}

	// using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
	// cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
	private static String hash(
			final String password,
			byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = f.generateSecret(new PBEKeySpec(
				password.toCharArray(), salt, iterations, desiredKeyLen)
		);
		return Base64.encodeBase64String(key.getEncoded());
	}
}
