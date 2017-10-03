package com.hyperiongray.framework;

import java.math.BigInteger;
import java.security.SecureRandom;

public class UowHelper{

	private static SecureRandom random = new SecureRandom();

	public static String generateNextUow() {
		return new BigInteger(130, random).toString(32);
	}

}
