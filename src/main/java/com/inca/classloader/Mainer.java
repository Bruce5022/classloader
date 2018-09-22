package com.inca.classloader;

public class Mainer {

	public static void main(String[] args) throws ClassNotFoundException {
		String string = new ClassLoaderAttachment().toString();
		System.out.println(string);
		Class<?> loadClass = new MyClassLoader("upload").loadClass("ClassLoaderAttachment");
		System.out.println(loadClass);
	}
}
