package com.cditie.restor.restor_client.view.page.open;

import com.cditie.restor.restor_client.App;
import com.cditie.restor.restor_client.RestorConstants;
import com.cditie.restor.restor_client.data.UserData;
import com.cditie.restor.restor_client.data.bo.TimeBO;
import com.cditie.restor.restor_client.entity.UserService;
import com.cditie.restor.restor_client.util.ViewUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 时间配置页面
 * Created by zhuyunhui on 7/31/2017.
 */
public class TimeConfigPage extends JFrame{

	public TimeConfigPage(){

		UserService userService = App.SpringContext.getBean(UserService.class);
		UserData userData = userService.getUserData();
		int wTime = userData.getTimeBO()!=null && userData.getTimeBO().getWorkTime()!=null ? userData.getTimeBO().getWorkTime() : RestorConstants.DEFAULT_WORK_TIME;
		int rTime = userData.getTimeBO()!=null && userData.getTimeBO().getRestTime()!=null ? userData.getTimeBO().getRestTime() : RestorConstants.DEFAULT_REST_TIME;

		this.setSize(500,500);
		this.setLocation(ViewUtil.getOpenLocation(this)[0],ViewUtil.getOpenLocation(this)[1]);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 10;
		c.ipadx = 20;
		this.setLayout(gridbag);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		ViewUtil.makeComponent(JLabel.class,this, "休息方式", gridbag, c,0,0);

		final JComboBox cbx = (JComboBox) ViewUtil.makeComponent(JComboBox.class,this, "", gridbag, c,1,0);
		cbx.setSize(10,50);
		RestorConstants.RestorStyleEnum[] restorStyleEnums = RestorConstants.RestorStyleEnum.values();
		for(RestorConstants.RestorStyleEnum restorStyleEnum:restorStyleEnums){
			cbx.addItem(restorStyleEnum.getName());
			if(userData.getTimeBO()!=null && userData.getTimeBO().getRestStyle().equals(restorStyleEnum.getName())){
				cbx.setSelectedItem(restorStyleEnum.getName());
			}

		}

		ViewUtil.makeComponent(JLabel.class,this, "工作时间", gridbag, c,0,1);
		final JTextField workTime = (JTextField)ViewUtil.makeComponent(JTextField.class,this, String.valueOf(wTime), gridbag, c,1,1);

		ViewUtil.makeComponent(JLabel.class,this, "休息时间", gridbag, c,0,2);
		final JTextField restTime = (JTextField)ViewUtil.makeComponent(JTextField.class,this, String.valueOf(rTime), gridbag, c,1,2);

		JButton saveButton = (JButton)ViewUtil.makeComponent(JButton.class,this, "保存", gridbag, c,1,3);
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.PLAIN_MESSAGE);
				UserService userService = App.SpringContext.getBean(UserService.class);
				UserData userData = userService.getUserData();

				//保存配置信息
				TimeBO timeBO = userData.getTimeBO()!=null ? userData.getTimeBO():new TimeBO();
				timeBO.setRestStyle(cbx.getSelectedItem().toString());
				timeBO.setWorkTime(Integer.valueOf(workTime.getText()));
				timeBO.setRestTime(Integer.valueOf(restTime.getText()));
				userData.setTimeBO(timeBO);

				try {
					userService.saveUserDate(userData);
				} catch (Exception e1) {
					e1.printStackTrace();
				}


			}

		});


	}




}
