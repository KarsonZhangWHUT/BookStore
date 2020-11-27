package com.karson.test;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.karson.bean.User;

public class BeanUtilsTest {

	@Test
	public void test1() throws Exception {
		Class<User> clazz = User.class;
		User user = clazz.newInstance();

		// 获取User类的所有的属性，保存在一个数组中
		Field[] fields = clazz.getDeclaredFields();
		// 遍历属性数组
		for (Field field : fields) {
			// 获取属性的名称
			String name = field.getName();
			// 根据属性的名称给属性设置值
			BeanUtils.setProperty(user, name, "haha");
		}
		System.out.println(user);
	}

	@Test
	public void test2() {

		Student student = new Student();

		try {
			BeanUtils.setProperty(student, "id", "123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(student);

	}

	/*
	 * 
	 * 
	 * 
	 */

}
