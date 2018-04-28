package com.cditie.restor.restor_client.view.page;

import com.cditie.restor.restor_client.App;
import com.cditie.restor.restor_client.RestorConstants;
import com.cditie.restor.restor_client.data.bo.TimeBO;
import com.cditie.restor.restor_client.entity.UserService;
import com.cditie.restor.restor_client.util.ViewUtil;
import com.cditie.restor.restor_client.view.page.open.AboutPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.*;

import com.cditie.restor.restor_client.view.page.open.RestorNoticePage;
import org.springframework.scheduling.config.IntervalTask;

public class HomePage extends JPanel {
	public static boolean startFlag = false;

	public HomePage() {
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		this.setLayout(gridbag);
		

		final JLabel styleLabel = (JLabel)ViewUtil.makeComponent(JLabel.class,this, "", gridbag, c,0,0);
		
		c.ipady = 200;
		final JLabel numLabel = (JLabel)ViewUtil.makeComponent(JLabel.class,this, "", gridbag, c,0,1);
		numLabel.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		final SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		numLabel.setText(sf.format(new Date()));

		java.util.Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				numLabel.setText(sf.format(new Date()));
			}
		}, 0, 1000);
		
		
		c.fill = GridBagConstraints.CENTER;
		c.ipady = 0;
		final JLabel textLabel = (JLabel)ViewUtil.makeComponent(JLabel.class,this, "", gridbag, c,0,2);
		final JButton button = (JButton)ViewUtil.makeComponent(JButton.class,this, "开始", gridbag, c,0,3);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!startFlag){
					count(styleLabel, textLabel);
				}

			}
		});

		

	}
	
	private void count(JLabel styleLabel,JLabel textLabel){
		try {
			startFlag = true;
			UserService userService = App.SpringContext.getBean(UserService.class);
			TimeBO timeBO = userService.getUserData().getTimeBO();
			if (timeBO != null) {
				System.out.println("timer do .......................");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmm");
				styleLabel.setText(timeBO.getRestStyle());
				final int nowM = Integer.valueOf(simpleDateFormat.format(new Date()));
				final Robot myRobot = new Robot();
				final long intervalTask = 1000;
				java.util.Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						int nowRunM = Integer.valueOf(simpleDateFormat.format(new Date()));
						//System.out.println(nowRunM);
						if(timeBO.getRestStyle().equals(RestorConstants.RestorStyleEnum.TomatoEasy.getName())){
							if((nowRunM-nowM) % (timeBO.getWorkTime() + timeBO.getRestTime()) < timeBO.getWorkTime()){
								//System.out.println("work");
								textLabel.setText("现在属于工作时间");

							}else{
								if(!"现在属于休息时间".equals(textLabel.getText())){
									textLabel.setText("现在属于休息时间");
									RestorNoticePage restorNoticePage = new RestorNoticePage();
									restorNoticePage.fresh();
									restorNoticePage.setAlwaysOnTop(true);

								}
								//System.out.println("rest");
							}
							
						}else{
							int miniter = new Date().getMinutes();
							if (miniter >= timeBO.getWorkTime()) {
								//System.out.println("rest");
								myRobot.mouseMove(0, 0);
								textLabel.setText("现在属于休息时间");
							}else{
								//System.out.println("work");
								textLabel.setText("现在属于工作时间");
							}
						}
						
						
					}
				}, 0, intervalTask);


			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	

}
