package com.cditie.restor.restor_client.entity;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.restor_client.RestorConstants;
import com.cditie.restor.restor_client.data.UserData;
import com.google.common.io.Files;

import javax.annotation.PostConstruct;


@Component
public class UserService {

	private UserData userData = new UserData();

	public String userName = System.getProperties().getProperty("user.name");

	public String userHome = System.getProperties().getProperty("user.home");

	public String fileName = userName + "@restor.dat";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 注册一个用户资料文件，保持用户的设置
	 * @throws UnknownHostException 
	 */
	@PostConstruct
	public void register() throws Exception{
		System.out.println("user register...");
		//用户目录生成一个user@restor.dat 
		userData.setUserName(userName);
		logger.info("filePath:" + userHome + File.separator + fileName);
		File file = new File(userHome + File.separator + fileName);
		if(!file.exists()){
			Files.write(JSONObject.toJSONString(userData).getBytes(),file);
		}else{
			setUserData(getLocalUserData());
		}
	}

	public void saveUserDate(UserData userData) throws Exception{
		userData.setUserName(userName);
		File file = new File(userHome + File.separator + fileName);
		if(!file.exists()){
			Files.write(JSONObject.toJSONString(userData).getBytes(),file);
		}else{
			file.delete();
			Files.write(JSONObject.toJSONString(userData).getBytes(),file);
			setUserData(userData);
		}
	}
	

	public UserData getLocalUserData(){
		Properties properties = System.getProperties();
		String userHome = properties.getProperty("user.home");
		File file = new File(userHome + File.separator + fileName);
		if(file.exists()){
			try {
				UserData data = JSONObject.parseObject(new String(Files.toByteArray( file),"utf8"),UserData.class);
				logger.info("file data is :" + JSONObject.toJSONString(data));
				return data;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return getUserData();
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
}
