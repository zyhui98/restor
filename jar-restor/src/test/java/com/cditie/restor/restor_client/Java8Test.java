package com.cditie.restor.restor_client;

import java.util.Arrays;

/**
 * @author zhuyunhui
 * @since 10/30/2017
 */
public class Java8Test {
	public static void main(String[] args) {
		int[] numbers = {1,1,2,2,2,2,2,2,2};
		int x= Arrays.stream(numbers).reduce(0,(a,b)-> a+b);
		System.out.println(x);
		Arrays.stream(numbers).forEach(a->{
			System.out.println(a);
		});
	}
}
