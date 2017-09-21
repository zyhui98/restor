package com.cditie.restor.restor_client.view.page.open;

import com.cditie.restor.crawler.dao.mybatis.MybatisStoreDAO;
import com.cditie.restor.restor_client.App;
import com.cditie.restor.restor_client.entity.UserService;
import com.cditie.restor.restor_client.util.ViewUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
/*		java.util.List<Map<String, Object>> list = Lists.newArrayList();
		Map<String,Object> map = Maps.newHashMap();
		map.put("title", "title");
		map.put("image","http://img04.sogoucdn.com/v2/thumb/resize/w/580/t/1/?appid=200547&url=http://img04.sogoucdn.com/app/a/200547/bf61a53c6d92e4114b65043e880cc0bd.jpg");
		map.put("content", "dfdfdffffffff<br>1<br><br>1<br><br>1<b<br>1<br><br>1<br><br>1<br>r><br>1<br>2</br>3</br>4<br>");
		list.add(map);*/

		final JLabel jLabelContent = new JLabel();
		setBlog(list, jLabelContent);

		JPanel jPanel = new JPanel();
		jPanel.setAutoscrolls(true);
		//jPanel.setPreferredSize(new Dimension(799,500));
		jPanel.add(jLabelContent);

		final JScrollPane jScrollPane =  new JScrollPane();
		jScrollPane.setPreferredSize(new Dimension(700,500));
		jScrollPane.setAutoscrolls(true);
		jScrollPane.setVisible(true);
		jScrollPane.setViewportView(jPanel);
		this.getContentPane().add(jScrollPane);

		JButton jButton = new JButton("换一换");
		jButton.setSize(100,50);
		this.add(jButton);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setVisible(true);


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
			final String image = "<html><h1>" + blog.get("title") + "</h1><div><img src='" + blog.get("image") + "'/></div></html>";
			jLabelContent.setText(image);
		}
	}

	public static void main(String[] args) {
		RestorNoticePage restorNoticePage = new RestorNoticePage();
		restorNoticePage.fresh();
	}


}
