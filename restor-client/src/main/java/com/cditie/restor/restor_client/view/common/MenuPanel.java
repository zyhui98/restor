package com.cditie.restor.restor_client.view.common;

import com.cditie.restor.restor_client.view.page.ActivePage;
import com.cditie.restor.restor_client.view.page.HomePage;
import com.cditie.restor.restor_client.view.page.open.AboutPage;
import com.cditie.restor.restor_client.view.page.open.TimeConfigPage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


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

		timeMenuItemListener(timeMenuItem);
		aboutMenuItemListener(aboutMenuItem);
		
		this.add(jMenuBar);
		
		
	}

	private void timeMenuItemListener(JMenuItem jMenuItem){
		jMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("timeMenuItemListener===============================");
				TimeConfigPage page = new TimeConfigPage();
				page.setVisible(true);
			}
		});

	}

	private void aboutMenuItemListener(JMenuItem jMenuItem){
		jMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("aboutMenuItemListener===============================");
				AboutPage aboutPage = new AboutPage();
				aboutPage.setVisible(true);
			}
		});

	}

	private void homeMenuListener(JMenu jMenu){
		jMenu.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				ActivePage.FAC.showPanel(HomePage.class);
			}
		});

	}


}
