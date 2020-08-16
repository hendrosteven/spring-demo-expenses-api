package com.kelaskoding.utilty;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Generator {

	public static String generate(String input) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		byte[] messageDigest = md.digest(input.getBytes()); 
		BigInteger no = new BigInteger(1, messageDigest); 
        String hashtext = no.toString(16); 
        while (hashtext.length() < 32) { 
            hashtext = "0" + hashtext; 
        } 
        return hashtext; 
	}
}
