package com.tams.util;

import com.tams.domain.Student;

import java.lang.reflect.Field;


public class TestUtil{
	
	

	public static void main(String[] args) {
	  
		Student student = new Student();
		
	   Class<?> clazz  = Student.class;
	   try {
		Field field = clazz.getDeclaredField("id");
		field.setAccessible(true);
		Object object = field.get(student);
		System.out.println(object);
	} catch (NoSuchFieldException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	}

	
}
	

