package com.cditie.restor.restor_client.view.page;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HomePage extends JPanel{
	
	public void initPanel(){
		
		JTextArea jTextArea = new JTextArea();
		jTextArea.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		
		jTextArea.setText("请先设置休息模式\n开始美好的一天");
		
		
		
		
		
		this.add(jTextArea);
	}

}
