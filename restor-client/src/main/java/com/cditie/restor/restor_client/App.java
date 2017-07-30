package com.cditie.restor.restor_client;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.cditie.restor.restor_client.view.MainFrame;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World!");

		try {
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			UIManager.put("RootPane.setupButtonVisible", false);
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO exception
		}

		new MainFrame("restor").start();

		Thread.sleep(1000 * 60);
		
		System.exit(0);

	}
}
