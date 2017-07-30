package com.cditie.restor.restor_client.entity;

import java.io.File;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.restor_client.RestorConstants;
import com.cditie.restor.restor_client.data.UserData;
import com.google.common.io.Files;


@Component
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 注册一个用户资料文件，保持用户的设置
	 * @throws UnknownHostException 
	 */
	public void register() throws Exception{
		//用户目录生成一个user@restor.dat 
		InetAddress.getLocalHost().getHostName();
		
		
		String fileName = getDataFile();
		

		Properties properties = System.getProperties();
		String userName = properties.getProperty("user.name");
		UserData userData = new UserData();
		userData.setUserName(userName);
		
		String userHome = properties.getProperty("user.home");
		Files.write(JSONObject.toJSONString(userData).getBytes(), new File(userHome + File.pathSeparator + fileName));
		
		
		
		
		
	}
	
	private String getDataFile(){
		Properties properties = System.getProperties();
		String userName = properties.getProperty("user.name");
		String fileName = userName + "@restor.dat";
		
		logger.info("data file is :{} " ,fileName);
		
		return fileName;
	}
	
	public UserData getUser(){
		
		
		
		return null;
		
	}

}
