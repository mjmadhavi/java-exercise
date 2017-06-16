package com.containerstore.common.util;

public class Format {
	public static final String titleCase(String str){
		char delimiter = ' ';
		char [] chars =  str.trim().toLowerCase().toCharArray();
		if (chars.length > 0 ){
			chars[0] = Character.toUpperCase(chars[0]);
			for (int i = 1;i< chars.length-1; i++){
			    if(chars[i] == delimiter)   chars[i+1] = Character.toUpperCase(chars[i+1]);	
			}
		}
		return String.valueOf(chars);
	}
	
	public static void main(String[] args){
		System.out.println(titleCase("hi how are you!"));
		System.out.println(titleCase("how are you 123"));
		System.out.println(titleCase("GOOD MORNING!"));
	}
}
