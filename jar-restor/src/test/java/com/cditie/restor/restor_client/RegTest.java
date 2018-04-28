package com.cditie.restor.restor_client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhuyunhui
 * @since 11/2/2017
 */
public class RegTest {

	public static void main(String[] args) {
		String s = "^(ls|invoke|count|status)+.*$";

		Pattern p= Pattern.compile(s);
		Matcher m=p.matcher("l1s UserService");
		System.out.println(m.matches());
	}
}
