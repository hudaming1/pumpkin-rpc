package org.hum;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class TmpTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = new String("A");
		System.out.println(s.getBytes("utf-8").length);
		System.out.println(s.getBytes("ASCII").length);
		System.out.println(Arrays.toString(s.getBytes("utf-8")));
		System.out.println(Arrays.toString(s.getBytes("ASCII")));
	}
}
