package com.cditie.restor.restor_client.view.page.open;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cditie.restor.restor_client.App;
import com.cditie.restor.restor_client.RestorConstants;
import com.cditie.restor.restor_client.data.UserData;
import com.cditie.restor.restor_client.data.bo.TimeBO;
import com.cditie.restor.restor_client.entity.UserService;
import com.cditie.restor.restor_client.util.ViewUtil;

public class SystemConfigPage extends JFrame{
	
	public SystemConfigPage(){
		UserService userService = App.SpringContext.getBean(UserService.class);
		UserData userData = userService.getUserData();
		int wTime = userData.getTimeBO()!=null && userData.getTimeBO().getWorkTime()!=null ? userData.getTimeBO().getWorkTime() : RestorConstants.DEFAULT_WORK_TIME;
		int rTime = userData.getTimeBO()!=null && userData.getTimeBO().getRestTime()!=null ? userData.getTimeBO().getRestTime() : RestorConstants.DEFAULT_REST_TIME;

		this.setSize(500,500);
		this.setLocation(ViewUtil.getOpenLocation(this)[0],ViewUtil.getOpenLocation(this)[1]);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(10,1));
		final JCheckBox mouseSelect = (JCheckBox)ViewUtil.makeComponent(JCheckBox.class, jPanel, "休息时间，禁止鼠标");
		final JCheckBox windowSelect = (JCheckBox)ViewUtil.makeComponent(JCheckBox.class, jPanel, "休息时间，弹出提示框");
		final JCheckBox windowSelect1 = (JCheckBox)ViewUtil.makeComponent(JCheckBox.class, jPanel, "休息时间，禁止键盘操作");
		JButton saveButton = (JButton)ViewUtil.makeComponent(JButton.class,jPanel, "保存");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.PLAIN_MESSAGE);
				UserService userService = App.SpringContext.getBean(UserService.class);
				UserData userData = userService.getUserData();

				//保存配置信息
				TimeBO timeBO = userData.getTimeBO()!=null ? userData.getTimeBO():new TimeBO();
				timeBO.setIsMouseConfig(mouseSelect.isSelected());
				timeBO.setIsWindowConfig(windowSelect.isSelected());
				userData.setTimeBO(timeBO);

				try {
					userService.saveUserDate(userData);
				} catch (Exception e1) {
					e1.printStackTrace();
				}


			}

		});
		
		
		this.add(jPanel);


		
	}

}
