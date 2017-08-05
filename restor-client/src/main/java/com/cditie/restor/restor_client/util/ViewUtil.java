package com.cditie.restor.restor_client.util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zhuyunhui on 8/1/2017.
 */
public class ViewUtil {


	public static int[] getOpenLocation(Container container){
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		int hight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - container.getHeight() - insets.bottom;
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - container.getWidth();
		return new int []{width/2,hight/2};
	}

	public static Component makebutton(Container frame, String name, GridBagLayout gridbag,
	                                   GridBagConstraints c,int x,int y ) {
		c.gridx = x;
		c.gridy = y;
		JButton button = new JButton(name);
		gridbag.setConstraints(button, c);
		c.fill = GridBagConstraints.BOTH;
		frame.add(button);
		return button;
	}

	public static Component makeLabel(Container frame, String name, GridBagLayout gridbag,
	                                   GridBagConstraints c,int x,int y ) {
		c.gridx = x;
		c.gridy = y;
		JLabel button = new JLabel(name);
		gridbag.setConstraints(button, c);
		c.fill = GridBagConstraints.BOTH;
		frame.add(button);
		return button;
	}

	public static Component makeTextField(Container frame, String name, GridBagLayout gridbag,
	                                   GridBagConstraints c,int x,int y ) {
		c.gridx = x;
		c.gridy = y;
		JTextField button = new JTextField(name);
		gridbag.setConstraints(button, c);
		c.fill = GridBagConstraints.BOTH;
		frame.add(button);
		return button;
	}

	public static Component makeComboBox(Container frame, JComboBox jComboBox, GridBagLayout gridbag,
	                                      GridBagConstraints c,int x,int y ) {
		c.gridx = x;
		c.gridy = y;
		gridbag.setConstraints(jComboBox, c);
		c.fill = GridBagConstraints.BOTH;
		frame.add(jComboBox);
		return jComboBox;
	}

}
