package com.tmg.generator.service;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "GoVind Hello";
		System.out.println(s);
		s = s.substring(0, 1).toLowerCase().trim() + s.substring(1);
		s= s.replaceAll("\\s","");
		System.out.println(s);

	}

}
