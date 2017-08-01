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

	public static ActivePage FAC = new ActivePage();

	private ActivePage(){

	}

	private Map<String, JPanel> pMap = new HashMap();

	public void showPanel(Class jPanel) {
		if (pMap.containsKey(jPanel.getName())) {
			pMap.forEach((k, v) -> {
				v.setVisible(false);
				if (k.equals(jPanel.getName())) {
					v.setVisible(true);
				}
			});
		} else {
			try {
				pMap.forEach((k, v) -> {
						v.setVisible(false);
				});
				pMap.put(jPanel.getName(), (JPanel)jPanel.newInstance());
				FAC.add(pMap.get(jPanel.getName()));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void initPanel() {
		showPanel(HomePage.class);
		if(pMap.values().size() == 0){
			showPanel(NoticePage.class);
		}
	}

}
