package com.cditie.restor.restor_client;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cditie.restor.restor_client.entity.UserService;
import com.cditie.restor.restor_client.view.MainFrame;

/**
 * Hello world!
 *
 */
public class App {
	
	public static ApplicationContext SpringContext = null;
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Work and Rest");
		SpringContext = new ClassPathXmlApplicationContext("restor.xml");
		new MainFrame("restor").start();

	}

}
