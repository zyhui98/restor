package com.cditie.restor.restor_client.data;

import java.io.Serializable;
import java.util.Properties;

import com.cditie.restor.restor_client.data.bo.TimeBO;
import lombok.Data;


@Data
public class UserData implements Serializable{
	
	
	private String userName;
	
	private String password;

	private TimeBO timeBO;
	
	
	
	

}
