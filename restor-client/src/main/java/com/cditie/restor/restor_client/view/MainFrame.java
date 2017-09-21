package com.cditie.restor.restor_client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cditie.restor.restor_client.listener.MainWindowListener;
import com.cditie.restor.restor_client.view.common.MenuPanel;
import com.cditie.restor.restor_client.view.page.ActivePage;

/**
 * 主框架
 * @author jonny
 * @date 2017年7月30日 下午11:24:42
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 构造函数
	public MainFrame(String str) {
		super(str);
		this.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		this.setBackground(Color.white);
		/*Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		int hight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - this.getHeight() - insets.bottom;
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - this.getWidth();
		
		this.setLocation(width/2,hight/2);*/
		
		MainLayout mainLayout = new MainLayout();
		this.setLayout(mainLayout);
		
		MenuPanel menuPanel = new MenuPanel();
		menuPanel.initMenu();
		
		this.add(menuPanel,BorderLayout.NORTH);
		ActivePage.FAC.initPanel();

		this.add(ActivePage.FAC,BorderLayout.CENTER);
		
		addWindowListener(new MainWindowListener());
		
		
		
	}
	
	public void start(){
		this.setVisible(true);
	}

}




