package com.cditie.restor.restor_client;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhuyunhui on 10/24/2017.
 */
public class MapTest {
	public static void main(String[] args) {
		String s1 = "";
		String s2 = null;
		System.out.println(s1.equals(s2));
		HashMap<String, String> hashMap = new HashMap<>();
		System.out.println(hashMap.put("key", "value"));
		System.out.println(hashMap.put("key", "value2"));
		ConcurrentHashMap<String, String> CACHE = new ConcurrentHashMap<>();
		CACHE.put("test","value");
		System.out.println(CACHE.putIfAbsent("test1","value1"));
		System.out.println(CACHE.putIfAbsent("test","valuevalue"));
		System.out.println("===============");
		System.out.println(CACHE.get("test"));
	}
}
