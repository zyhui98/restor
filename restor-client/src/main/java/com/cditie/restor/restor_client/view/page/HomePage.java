package com.cditie.restor.restor_client.view.page;

import com.cditie.restor.restor_client.App;
import com.cditie.restor.restor_client.data.bo.TimeBO;
import com.cditie.restor.restor_client.entity.UserService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class HomePage extends JPanel {

	public HomePage() {

		JLabel workLabel = new JLabel();
		workLabel.setText("好好工作、天天向上");
		workLabel.setVisible(true);

		JLabel restLabel = new JLabel();
		restLabel.setText("休息是一种享受");
		restLabel.setVisible(false);


		final JLabel numLabel = new JLabel();
		numLabel.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		final SimpleDateFormat sf = new SimpleDateFormat("HH:ss");
		numLabel.setText(sf.format(new Date()));

		java.util.Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				numLabel.setText(sf.format(new Date()));
			}
		}, 0, 1000);

		this.setLayout(new GridLayout(3, 1));
		this.add(workLabel);
		this.add(restLabel);
		this.add(numLabel);

		try {
			UserService userService = App.SpringContext.getBean(UserService.class);
			TimeBO timeBO = userService.getUserData().getTimeBO();
			if (timeBO != null) {
				System.out.println("timer do .......................");
				final Robot myRobot = new Robot();
				Timer timerCount = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						int miniter = new Date().getMinutes();
						if (miniter > 50) {
							myRobot.mouseMove(0, 0);
						}
						/*myRobot.keyPress( KeyEvent.VK_R);
						myRobot.keyPress( KeyEvent.VK_E);
						myRobot.keyPress( KeyEvent.VK_S);
						myRobot.keyPress( KeyEvent.VK_T);
						myRobot.keyPress( KeyEvent.VK_O);
						myRobot.keyPress( KeyEvent.VK_R);*/
					}
				}, 100, 1);


			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
