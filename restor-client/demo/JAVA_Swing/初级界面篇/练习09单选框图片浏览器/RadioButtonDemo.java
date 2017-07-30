//RadioButtonDemo.java
import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;

public class RadioButtonDemo extends JPanel{
	
	//定义字符串类型的常量
	static String aString = "a";
	static String bString = "b";
	static String cString = "c";
	static String dString = "d";
	static String eString = "e";
	JLabel picture;
		
	public RadioButtonDemo(){
		
		//定义单选框按钮a并且初始化按钮的属性
		JRadioButton aButton = new JRadioButton(aString);
		aButton.setMnemonic('a');
		aButton.setActionCommand(aString);
		aButton.setSelected(true);
		aButton.setBackground(Color.pink);
		
		//定义单选框按钮b并且初始化按钮的属性
		JRadioButton bButton = new JRadioButton(bString);
		bButton.setMnemonic('b');
		bButton.setActionCommand(bString);
		bButton.setBackground(Color.pink);
		
		//定义单选框按钮c并且初始化按钮的属性
		JRadioButton cButton = new JRadioButton(cString);
		cButton.setMnemonic('c');
		cButton.setActionCommand(cString);
		cButton.setBackground(Color.pink);
		
		//定义单选框按钮d并且初始化按钮的属性
		JRadioButton dButton = new JRadioButton(dString);
		dButton.setMnemonic('d');
		dButton.setActionCommand(dString);
		dButton.setBackground(Color.pink);
		
		//定义单选框按钮e并且初始化按钮的属性
		JRadioButton eButton = new JRadioButton(eString);
		eButton.setMnemonic('e');
		eButton.setActionCommand(eString);
		eButton.setBackground(Color.pink);
		
		//实例化ButtonGroup类，将上述定义好的五个单选框按钮加到其对象group之中
		ButtonGroup group = new ButtonGroup();
		group.add(aButton);
		group.add(bButton);
		group.add(cButton);
		group.add(dButton);
		group.add(eButton);
		
		//实例化RadioListener类
		//将上述定义好的五个单选框按钮加入事件监听
		RadioListener myListener = new RadioListener();
		aButton.addActionListener(myListener);
		bButton.addActionListener(myListener);
		cButton.addActionListener(myListener);
		dButton.addActionListener(myListener);
		eButton.addActionListener(myListener);
		
		picture = new JLabel(new ImageIcon("image/"+aString+".gif"));
		picture.setPreferredSize(new Dimension(200,200));
		
		//实例化JPanel的对象
		//将五个单选框按钮加入到其对象radioPanel之中
		JPanel radioPanel = new JPanel();
		//将五个单选框按钮按照0行1列的格式排列
		radioPanel.setLayout(new GridLayout(0,1));
		radioPanel.add(aButton);
		radioPanel.add(bButton);
		radioPanel.add(cButton);
		radioPanel.add(dButton);
		radioPanel.add(eButton);
	    
	    setBackground(Color.pink);	    
	    setLayout(new BorderLayout());
	    
	    //单选框按钮位于总面板布局的西方
	    add(radioPanel,BorderLayout.WEST);	    
	    //图片位于总面板布局的中央
	    add(picture,BorderLayout.CENTER);
	    
	    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	//对单选框按钮的事件响应
	class RadioListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			picture.setIcon(new ImageIcon("images/"+e.getActionCommand()+".gif"));
		}
	}
	
	//主函数，也是程序入口处
	public static void main(String s[]){
		JFrame jf = new JFrame("RadioButtonDemo");
		RadioButtonDemo rbd = new RadioButtonDemo();
		jf.setSize(300,300);
	    jf.setBackground(Color.pink);
		jf.setContentPane(rbd);
		jf.setVisible(true);
		
		jf.addWindowListener(new WindowAdapter() {	
			public void windowClosing(WindowEvent e){
				 System.exit(0);}
												
		});		
	}	
}	