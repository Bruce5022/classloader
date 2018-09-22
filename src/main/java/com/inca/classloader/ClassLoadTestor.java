package com.inca.classloader;

import java.net.URL;

/**
 * Hello world!
 *
 */
public class ClassLoadTestor {
	public static void main(String[] args) {
		// *****************************查看自定义类和System的加载器**********************************//
		ClassLoader classLoader = ClassLoadTestor.class.getClassLoader();
		System.out.println("自定义类的类加载器:" + classLoader);
		System.out.println("自定义类的类加载器名字1:" + classLoader.getName());
		System.out.println("自定义类的类加载器名字2:" + classLoader.getClass().getName());

//		ClassLoader systemLoader = System.class.getClassLoader();
//		System.out.println("系统类的类加载器:" + systemLoader);
//		System.out.println("系统类的类加载器名字1:" + systemLoader.getName());
//		System.out.println("系统类的类加载器名字2:" + systemLoader.getClass().getName());

		// ****************************查看类加载器的继承关系************************************//
		while (classLoader != null) {
			System.out.print("类加载器[" + classLoader);
			classLoader = classLoader.getParent();
			System.out.println("]的父类加载器是[" + classLoader + "]");
		}
		/**
		 * 将自己的程序打包成jar包,放在jdk的jre路径的ext包下,测试环境代码获取测试类的加载器,
		 * 变成Ext开头的加载器,这个就是父类优先加载机制的结果
		 * 从这里发现两个问题:
		 * 1.父类加载机制
		 * 2.jdk10中的包结构已经发生了变化,找不到这样的包结构了
		 * 3.以后jdk版本的变化,去官网看看
		 * https://docs.oracle.com/javase/9/migrate/toc.htm#JSMIG-GUID-7744EF96-5899-4FB2-B34E-86D49B2E89B6
		 * 
		 */
		// ****************************官网的例子************************************//
		URL systemResource = ClassLoader.getSystemResource("java/lang/Class.class");
		System.out.println(systemResource);
		//When run on JDK 8, this method returns a JAR URL of the form:
		//jar:file:/usr/local/jdk8/jre/lib/rt.jar!/java/lang/Class.class
//		A modular image doesn’t contain any JAR files, so URLs of this form make no sense. On JDK 9, this method returns:
//		jrt:/java.base/java/lang/Class.class
	}
}
//自定义类的类加载器:jdk.internal.loader.ClassLoaders$AppClassLoader@6659c656
//自定义类的类加载器名字1:app
//自定义类的类加载器名字2:jdk.internal.loader.ClassLoaders$AppClassLoader
//系统类的类加载器:null
//Exception in thread "main" java.lang.NullPointerException
//	at com.inca.classloader.ClassLoadTestor.main(ClassLoadTestor.java:16)
//类加载器[jdk.internal.loader.ClassLoaders$AppClassLoader@6659c656]的父类加载器是[jdk.internal.loader.ClassLoaders$PlatformClassLoader@299a06ac]
//类加载器[jdk.internal.loader.ClassLoaders$PlatformClassLoader@299a06ac]的父类加载器是[null]
//jrt:/java.base/java/lang/Class.class
