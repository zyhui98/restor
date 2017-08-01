package com.cditie.restor.restor_client.view.page.open;

import com.cditie.restor.restor_client.util.ViewUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * Created by zhuyunhui on 8/1/2017.
 */
public class AboutPage extends JFrame{


	public AboutPage(){
		this.setSize(500,500);
		this.setLocation(ViewUtil.getOpenLocation(this)[0],ViewUtil.getOpenLocation(this)[1]);
		JLabel version = new JLabel("Reator V1.0.0");
		version.setFont(new Font("TimesRoman", Font.BOLD,30));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		String time = simpleDateFormat.format(new Date());
		JLabel build = new JLabel("Build AT " + time );
		JLabel license = new JLabel("licensed to mobanker");
		JLabel powered = new JLabel("powered by zyhui98");
		JLabel rights = new JLabel("@ 2000-2017 cditie.com all rights reserved");
		rights.setFont(new Font("TimesRoman", Font.CENTER_BASELINE,10));


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
		jPanel.add(build);
		jPanel.add(license);
		jPanel.add(powered);
		jPanel.add(rights);
		this.add(jPanel);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));


	}
}
