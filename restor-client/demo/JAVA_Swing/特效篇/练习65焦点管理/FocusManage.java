//FocusManage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FocusManage extends JApplet {
	//制作按钮
	private JButton button_1 = new NotFocusTraversableButton(),
		button_2 = new ButtonThatManagesFocus(),
		button_3 = new JButton("规范的按钮"),
		button_4 = new JButton("规范的按钮"),
		button_5 = new JButton("不接受焦点的按钮"),
		button_6 = new JButton(
		"传送焦点的按钮");
	//初始化小程序		
	public void init() {
		//设置按钮的背景颜色
		button_1.setBackground(Color.orange);
		button_2.setBackground(Color.green);
		button_3.setBackground(Color.yellow);
		button_4.setBackground(Color.yellow);
		button_5.setBackground(Color.pink);
		button_6.setBackground(Color.red);
		
		//制作容器
		Container contentPane = getContentPane();
		FocusCycleRootPanel panel = new FocusCycleRootPanel();
                //不接受焦点
		button_5.setRequestFocusEnabled(false);
		//传递焦点
		button_6.setNextFocusableComponent(button_2);
                //加载按钮
		panel.add(button_3);
		panel.add(button_4);
		panel.add(button_5);
                //设置面板内容
		contentPane.setLayout(new FlowLayout());
		contentPane.add(button_1);
		contentPane.add(button_2);
		contentPane.add(panel);
		contentPane.add(button_6);
	}
}
//不传送焦点的按钮
class ButtonThatManagesFocus extends JButton {
	public ButtonThatManagesFocus() {
		super("不传送焦点的按钮");	
	}
	public boolean isManagingFocus() {
		return true;
	}
	public void processComponentKeyEvent(KeyEvent e) {
		System.out.println(e);
	}
}
//不接受焦点的按钮
class NotFocusTraversableButton extends JButton {
	public NotFocusTraversableButton() {
		super("不接受焦点的按钮");	
	}
	public boolean isFocusTraversable() {
		return false;
	}
}
//焦点循环面板
class FocusCycleRootPanel extends JPanel {
	public FocusCycleRootPanel() {
		setBorder(BorderFactory.createTitledBorder(
					"焦点循环面板"));
	}
	public boolean isFocusCycleRoot() {
		return true;
	}
}
