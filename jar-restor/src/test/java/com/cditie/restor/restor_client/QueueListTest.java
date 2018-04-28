package com.cditie.restor.restor_client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuyunhui on 9/30/2017.
 */
public class QueueListTest {
	public static void main(String[] args) {
		String[] s = new String[10];
		String line = "/dubbo/fx/sgp-web/com.mobanker.activity.sjd.contract.SjdWithdrawContract/1.0.0";
		String pattern = "/{1}(\\w+)/{1}(\\w+)/(.*){1}";

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find( )) {
			System.out.println(line.replace("/"+m.group(2),""));
		}

	}
}
