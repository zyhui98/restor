package com.cditie.restor.restor_client.data;

import java.io.Serializable;
import java.util.Properties;

import lombok.Data;


@Data
public class UserData implements Serializable{
	
	private Properties properties = new Properties();
	
	private String userName;
	
	private String password;
	
	
	

}
