package com.cditie.restor.restor_client.view.page;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cditie.restor.restor_client.RestorConstants;

import lombok.Data;

/**
 * 活动页面
 * @author jonny
 * @date 2017年7月30日 下午1:03:17
 *
 */
public class ActivePage extends JPanel{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActivePage(){
		super();

	}

	private Map<String, JPanel> pMap = new HashMap();

	private void showPanel(JPanel jPanel) {
		String name = jPanel.getName();
		if (pMap.containsKey(name)) {
			pMap.forEach((k, v) -> {
				v.setVisible(false);
				if (k.equals(name)) {
					v.setVisible(true);
				}
			});

		} else {
			pMap.put(name, jPanel);
			pMap.forEach((k, v) -> {
				v.setVisible(false);
			});
			this.add(jPanel);
			jPanel.setVisible(true);

		}
	}

	public void initPanel() {
		if(pMap.values().size() == 0){
			JPanel noticePanel = new NoticePage();
			showPanel(noticePanel);
		}
		//this.getActivePanel().setVisible(true);
	}

}
