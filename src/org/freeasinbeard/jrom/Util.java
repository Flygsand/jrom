package org.freeasinbeard.jrom;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	
	public static String str(byte[] data, int offset, int len) {
		try {
			return new String(data, offset, len, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			return new String(data, offset, len);
		}
	}
	
	public static String hash(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		return new BigInteger(1, md.digest(data)).toString(36);
	}
	
	/* assume data at offset is a 16-bit unsigned Little-Endian integer, 
	 * and convert it to a Java int */
	public static int uint16(byte[] data, int offset) {
		return ((data[offset] & 0xFF) << 8) + (data[offset+1] & 0xFF);
	}
	
	/* assume data at offset is a 32-bit unsigned Little-Endian integer, 
	 * and convert it to a Java long */
	public static long uint32(byte[] data, int offset) {
		return ((data[offset] & 0xFF) << 24) + ((data[offset+1] & 0xFF) << 16) +
			   ((data[offset+2] & 0xFF) << 8) + (data[offset+3] & 0xFF);
	}
	
	public static String join(String[] arr) {
		if (arr.length == 0)
			return "";
		
		StringBuffer sb = new StringBuffer(arr[0]);
		for (int i = 1; i < arr.length; i++)
			sb.append(arr[i]).append(' ');
		
		return sb.toString();
	}
}
