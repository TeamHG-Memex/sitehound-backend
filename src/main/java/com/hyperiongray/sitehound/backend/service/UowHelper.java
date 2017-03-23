package com.hyperiongray.sitehound.backend.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by tomas on 7/27/15.
 */
@Component
public class UowHelper{

	private SecureRandom random = new SecureRandom();

	public String generateNextUow() {
		return new BigInteger(130, random).toString(32);
	}

}
