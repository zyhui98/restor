package com.cditie.restor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhuyunhui
 * @since 11/19/2017
 */
@AutoConfigurationPackage
@SpringBootApplication
@MapperScan(basePackages = "com.cditie.restor.mapper")
@Controller
public class CditieServer {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CditieServer.class, args);
	}


}
