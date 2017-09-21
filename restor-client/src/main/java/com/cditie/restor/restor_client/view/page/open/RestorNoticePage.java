package com.cditie.restor.restor_client.view.page.open;

import com.cditie.restor.crawler.dao.mybatis.MybatisStoreDAO;
import com.cditie.restor.restor_client.App;
import com.cditie.restor.restor_client.entity.UserService;
import com.cditie.restor.restor_client.util.ViewUtil;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhuyunhui on 8/1/2017.
 */
public class RestorNoticePage extends JFrame{



	public RestorNoticePage(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(800,600);
		this.setLocation(ViewUtil.getOpenLocation(this)[0],ViewUtil.getOpenLocation(this)[1]);
	}

	public void fresh(){
		System.out.println(">>>>>>>>>>>>>>fresh");
		setPanel();

	}

	public void setPanel(){
		MybatisStoreDAO mybatisStoreDAO = App.SpringContext.getBean(MybatisStoreDAO.class);
		final java.util.List<Map<String,Object>> list = mybatisStoreDAO.getBlogContent();


		final JLabel jLabelContent = new JLabel();
		JButton jButton = new JButton("换一换");
		GridLayout gridLayout = new GridLayout(2,1);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(gridLayout);
		jPanel.add(jLabelContent);
		jPanel.add(jButton);

		final JScrollPane jScrollPane =  new JScrollPane();
		jScrollPane.setSize(700,500);
		jScrollPane.setAutoscrolls(true);
		jScrollPane.setVisible(true);
		jScrollPane.setViewportView(jPanel);
		this.add(jScrollPane);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setVisible(true);

		setBlog(list, jLabelContent);
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBlog(list, jLabelContent);
			}
		});

	}

	/**
	 * 设置内容
	 * @param jLabelContent
	 */
	private void setBlog(java.util.List<Map<String,Object>> list, JLabel jLabelContent) {
		Collections.shuffle(list);
		Map<String,Object> blog = list.get(0);
		System.out.println(blog);
		if(!StringUtils.isEmpty(blog.get("content"))){
			final String content = "<html><h1>" + blog.get("title") + "</h1><div style='width:600px;'>" + blog.get("content") + "</div></html>";
			jLabelContent.setText(content);
		}
		if(!StringUtils.isEmpty(blog.get("image"))){
			final String image = "<html><h1>" + blog.get("title") + "</h1><div style='position:relative; height:400px; overflow:auto'><img src='" + blog.get("image") + "'/></div></html>";
			jLabelContent.setText(image);
		}
		jLabelContent.setAutoscrolls(true);
	}


}
