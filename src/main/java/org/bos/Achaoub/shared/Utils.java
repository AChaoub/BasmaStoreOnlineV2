package org.bos.Achaoub.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;


@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHANUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuwxyz";

	
	public String generateUser(int length) {
		StringBuilder returnvalue = new StringBuilder(length);
		
		for(int i = 0;i<length;i++) {
			returnvalue.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
		}
		
		return new String(returnvalue);
	}
}
