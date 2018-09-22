package com.inca.classloader;

public class Mainer {

	public static void main(String[] args) {
		String string = new ClassLoaderAttachment().toString();
		System.out.println(string);
	}
}
