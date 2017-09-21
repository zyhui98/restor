package com.cditie.restor.restor_client.view.page.open;

import com.cditie.restor.restor_client.util.ViewUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhuyunhui on 8/1/2017.
 */
public class RestorNoticePage extends JFrame{


	public RestorNoticePage(){
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(500,500);
		this.setLocation(ViewUtil.getOpenLocation(this)[0],ViewUtil.getOpenLocation(this)[1]);
		JLabel version = new JLabel("你喜欢看笑话吗？");



		try {
			JLabel jlpic = new JLabel();
			InputStream stream = getClass().getResourceAsStream("/static/images/logo.png");
			ImageIcon icon= new ImageIcon(ImageIO.read(stream));
			System.out.println(icon.getImage());
			icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
					250, Image.SCALE_DEFAULT));
			System.out.println(icon.getIconHeight() + "" + icon.getIconWidth());
			jlpic.setBounds(0, 0, this.getWidth(), this.getHeight());
			jlpic.setHorizontalAlignment(0);
			jlpic.setIcon(icon);
			this.add(jlpic);
		} catch (IOException e) {
			e.printStackTrace();
		}

		GridLayout gridLayout = new GridLayout(5,1);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(gridLayout);
		jPanel.add(version);
		this.add(jPanel);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));


	}
}
