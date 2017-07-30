//JTabbedPaneTest.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//JTabbedPaneTest类的声明
public class JTabbedPaneTest extends JApplet {
	//对象实例化
	private JTabbedPane tp = new JTabbedPane(SwingConstants.BOTTOM);
	private JComboBox combo = new JComboBox();
	//构造函数
	public JTabbedPaneTest() {
		//容器
		Container contentPane = getContentPane();
		//对象化面板
		JPanel comboPanel = new JPanel();
		JPanel panelOne = new JPanel();
		JPanel panelTwo = new JPanel();
				
		panelOne.add(new JButton("当前选中的是第1个选项卡"));
		panelOne.setBackground(Color.cyan);
		panelTwo.add(new JButton("当前选中的是第2个选项卡"));
		panelTwo.setBackground(Color.orange);

		tp.add(panelOne, "Panel One");
		tp.add(panelTwo, "Panel Two");
		
		combo.addItem("TOP");
		combo.addItem("LEFT");
		combo.addItem("RIGHT");
		combo.addItem("BOTTOM");

		setComboValue();

		comboPanel.add(new JLabel("选项卡的位置:"));
		comboPanel.add(combo);

		contentPane.setLayout(new BorderLayout());
		contentPane.add(comboPanel, BorderLayout.NORTH);
		contentPane.add(tp, BorderLayout.CENTER);
		//响应用户操作
		combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				int state = e.getStateChange();

				if(state == ItemEvent.SELECTED) {
					String s = (String)cb.getSelectedItem();

					if(s.equals("TOP"))
						tp.setTabPlacement(JTabbedPane.TOP);
					else if(s.equals("LEFT"))
						tp.setTabPlacement(JTabbedPane.LEFT);
					else if(s.equals("RIGHT"))
						tp.setTabPlacement(JTabbedPane.RIGHT);
					else if(s.equals("BOTTOM"))
						tp.setTabPlacement(JTabbedPane.BOTTOM);

					tp.validate();
				}
			}
		});
	}
	//设置组合框的值
		private void setComboValue() {
		int placement = tp.getTabPlacement();
		String selectedItem = null;

		switch(placement) {
			case JTabbedPane.TOP:
					selectedItem = "TOP";
					break;
			case JTabbedPane.LEFT:
					selectedItem = "LEFT";
					break;
			case JTabbedPane.RIGHT:
					selectedItem = "RIGHT";
					break;
			case JTabbedPane.BOTTOM:
					selectedItem = "BOTTOM";
					break;
		}
		combo.setSelectedItem(selectedItem);
	}
}
