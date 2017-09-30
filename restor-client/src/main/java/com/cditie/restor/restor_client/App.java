package com.cditie.restor.restor_client;

import javax.swing.UIManager;

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
		UIManager.LookAndFeelInfo  []info=UIManager.getInstalledLookAndFeels() ;
		for(UIManager.LookAndFeelInfo tem:info)
		{
			System.out.println(tem.getClassName());
		}

		/*try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			System.out.println("Substance Raven Graphite failed to initialize");
		}*/

		System.out.println("Work and Rest");
		SpringContext = new ClassPathXmlApplicationContext("restor.xml");
		new MainFrame("restor").start();

	}

}
