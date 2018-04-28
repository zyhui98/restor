package com.cditie.restor.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhuyunhui
 * @since 11/19/2017
 */
@SpringBootApplication
@EnableAutoConfiguration
public class CrawlerServer {


	public static void main(String[] args) throws Exception {
		SpringApplication.run(CrawlerServer.class, args);
	}

}
