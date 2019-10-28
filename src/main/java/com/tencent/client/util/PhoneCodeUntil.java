package com.tencent.client.util;

import java.util.Random;

public class PhoneCodeUntil {
	public static String Random() {
		Random random = new Random();
	     String rad = String.valueOf(random.nextInt(Integer.MAX_VALUE));
	     String veriCode = rad.substring(rad.length()-4);
		return veriCode;
	}	 
}
