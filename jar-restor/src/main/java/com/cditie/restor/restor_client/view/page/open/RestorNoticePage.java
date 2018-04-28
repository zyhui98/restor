package com.cditie.restor.restor_client.view.page.open;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cditie.restor.restor_client.util.ViewUtil;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static com.oracle.webservices.internal.api.message.MessageContextFactory.createFactory;

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

	public  ClientHttpRequestFactory createFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(3000);
		factory.setReadTimeout(5000);
		return factory;
	}
	private RestTemplate restTemplate = new RestTemplate(createFactory());

	public  String getJsonResult(String url, Map<String,Object> paramsMust){
		return  restTemplate.getForObject(url, String.class, new HashMap<>());
	}

	public void setPanel(){
		final String urlTemp = "http://127.0.0.1:8080/happy";
		final JSONArray list = JSONObject.parseArray(getJsonResult(urlTemp,null));


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
	private void setBlog(JSONArray list, JLabel jLabelContent) {

		JSONObject blog = list.getJSONObject(new Random().nextInt(list.size()));
		System.out.println("setBlog=" + blog);
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
