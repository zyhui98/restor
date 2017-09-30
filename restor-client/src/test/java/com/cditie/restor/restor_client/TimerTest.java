package com.cditie.restor.restor_client;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhuyunhui on 9/29/2017.
 */
public class TimerTest {
	public static void main(String[] args) {
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					System.out.println(new Date().getTime() + "==========name:" + Thread.currentThread().getName() );
					batchAppend();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 1000, 1);

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					Field field = ReflectionUtils.findField(Timer.class,"queue");
					field.setAccessible(true);
					System.out.println(Thread.currentThread().getName()+"==========timer.size:" + new Gson().toJson(field.get(timer)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 1000, 100);


	}

	public static void batchAppend(){
		System.out.println("==========batchAppend=" + Thread.currentThread().getName() );
		try {
			//Thread.sleep(1000L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
