/**
 * 
 */
package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Fernando Costa Migliorini
 * @email fercosmig@gmail.com
 * @since Dec 27, 2019
 * @file util.Encryption.java
 */
public class Encryption {

	private static final Logger LOGGER = LogManager.getLogger(Encryption.class.getName());

	/**
	 * Constructors
	 */
	public Encryption() {
	}

	/**
	 * Methods
	 */
	public static String getMD5(String input) {
		LOGGER.info("iniciando metodo getMD5");

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger num = new BigInteger(1, messageDigest);
			String hashText = num.toString(16);
			while (hashText.length() < 32) {
				hashText = "0" + hashText;
			}
			return hashText;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
			throw new RuntimeException(e);
		}

	}
}
