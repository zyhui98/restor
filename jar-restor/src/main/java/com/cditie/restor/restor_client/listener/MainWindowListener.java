package com.cditie.restor.restor_client.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowListener extends WindowAdapter {

	public void windowClosing(WindowEvent e) {
		e.getWindow().dispose();
		System.exit(0);
	}

}
