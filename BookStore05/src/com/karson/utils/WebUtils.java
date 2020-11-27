package com.karson.utils;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


public class WebUtils {

	public static <T> T param2bean(HttpServletRequest request, T t) {

//		
//		/**
//		 * 自己用反射实现，会有类型转换问题
//		 */
//		// 获取所有声明的属性
//		Field[] fields = t.getClass().getDeclaredFields();
//		// 每个属性都有属性名
//		for (Field field : fields) {
//			//获取属性名
//			String name = field.getName();
////			在request中获取响应的属性值
//			String paramValue = request.getParameter(name);
//			//设置属性
//			field.setAccessible(true);
//			try {
//				field.set(t, paramValue);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

		/**
		 * 使用BeanUtils工具
		 */
		// 1.获取所有声明的属性
		Field[] fields = t.getClass().getDeclaredFields();
		// 2.每个属性都有属性名
		for (Field field : fields) {
			// 3.获取属性名
			String name = field.getName();
			// 在request中根据参数名，获取参数的属性值
			String paramValue = request.getParameter(name);
//			System.out.println(name+paramValue);
			try {
				BeanUtils.setProperty(t, name, paramValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	
	/*
	 * 因为在request中添加了一个请求参数【method-regist/login】所以没办法把请求参数构成的Map映射给对象的属性
	 */
	public static<T> T param2bean2(HttpServletRequest request, T t) {
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}
	
}
