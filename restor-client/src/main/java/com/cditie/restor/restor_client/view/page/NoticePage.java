package com.cditie.restor.restor_client.view.page;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Timestamp;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.cditie.restor.restor_client.RestorConstants;

public class NoticePage extends JPanel{
	
	public NoticePage(){
		super();
		this.setName(RestorConstants.NOTICE_PANEL);
		
		JLabel JLabel = new JLabel();
		JLabel.setFont(new Font("TimesRoman", Font.PLAIN,20));
		JLabel.setText("请先设置休息模式开始美好的一天");
		
		
		//test robot
		JButton jButton = new JButton("鼠标控制0,0");
		jButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				try {
					final Robot myRobot = new Robot();
					
					
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							myRobot.mouseMove(0, 0);
						}
					}, 100,100);
					
					
					
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
				
				
				
			}
		});
		
		this.add(jButton);
		this.add(JLabel);
	}

}
