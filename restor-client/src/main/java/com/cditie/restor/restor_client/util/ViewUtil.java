package com.cditie.restor.restor_client.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Created by zhuyunhui on 8/1/2017.
 */
public class ViewUtil {

	public static int[] getOpenLocation(Container container) {
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		int hight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - container.getHeight()
				- insets.bottom;
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - container.getWidth();
		return new int[] { width / 2, hight / 2 };
	}

	public static JComponent makeComponent(Class<? extends JComponent> clazz, Container frame, String name,
			GridBagLayout gridbag, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		JComponent component = null;
		try {
			component = (JComponent) clazz.newInstance();
			if (component instanceof JLabel) {
				((JLabel) component).setText(name);
			}
			if (component instanceof JTextField) {
				((JTextField) component).setText(name);
			}
			if (component instanceof JButton) {
				((JButton) component).setText(name);
			}
			if (component instanceof JCheckBox) {
				((JCheckBox) component).setText(name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		gridbag.setConstraints(component, c);
		c.fill = GridBagConstraints.BOTH;
		frame.add(component);
		return component;
	}

	public static JComponent makeComponent(Class<? extends JComponent> clazz, Container frame, String name) {
		JComponent component = null;
		try {
			component = (JComponent) clazz.newInstance();
			if (component instanceof JLabel) {
				((JLabel) component).setText(name);
			}
			if (component instanceof JTextField) {
				((JTextField) component).setText(name);
			}
			if (component instanceof JButton) {
				((JButton) component).setText(name);
			}
			if (component instanceof JCheckBox) {
				((JCheckBox) component).setText(name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.add(component);
		return component;
	}

}
