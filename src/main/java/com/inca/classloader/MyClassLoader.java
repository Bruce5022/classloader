package com.inca.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader {
	private String classDir;

	public MyClassLoader() {
	}

	public MyClassLoader(String classDir) {
		this.classDir = classDir;
	}

	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}

	public MyClassLoader(String name, ClassLoader parent) {
		super(name, parent);
	}

	public static void main(String[] args) throws IOException {
		// 测试复制代码
		System.out.println("################################");
		String srcPath = args[0];
		FileInputStream fis = new FileInputStream(srcPath);
		String destPath = args[1];
		String destFileName = srcPath.substring(srcPath.lastIndexOf('\\') + 1);
		FileOutputStream fos = new FileOutputStream(destPath + "\\" + destFileName);
		cypher(fis, fos);
		fos.close();
		fis.close();
//		String string = new ClassLoaderAttachment().toString();
//		System.out.println("################################"+string);
		System.out.println("################################");
	}

	public static void cypher(InputStream ips, OutputStream ops) throws IOException {
		int b = -1;
		while ((b = ips.read()) != -1) {
			ops.write(b ^ 0xff);
		}
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String classFileName = classDir + "\\" + name + ".class";
		try {
			FileInputStream fis = new FileInputStream(classFileName);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			cypher(fis, bos);
			fis.close();
			byte[] bytes = bos.toByteArray();
			bos.close();
			Class<?> defineClass = defineClass(bytes, 0, bytes.length);
			return defineClass;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}
}
