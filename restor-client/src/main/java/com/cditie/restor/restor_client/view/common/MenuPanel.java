package com.cditie.restor.restor_client.view.common;

import java.awt.FlowLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MenuPanel extends JPanel{
	
	public MenuPanel(){
		super();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	public void initMenu(){

		JMenuBar jMenuBar = new JMenuBar();
		
		JMenu homeMenu = new JMenu("首页");
		
		JMenu settingMenu = new JMenu("设置");
		JMenuItem timeMenuItem = new JMenuItem("时间设定");
		JMenuItem systemMenuItem = new JMenuItem("系统设置");
		settingMenu.add(timeMenuItem);
		settingMenu.add(systemMenuItem);
		
		JMenu helpMenu = new JMenu("帮助");
		JMenuItem aboutMenuItem = new JMenuItem("关于");
		helpMenu.add(aboutMenuItem);
		
		
		jMenuBar.add(homeMenu);
		jMenuBar.add(settingMenu);
		jMenuBar.add(helpMenu);
		
		this.add(jMenuBar);
		
		
	}

}
