//JRadioButtonTest.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JRadioButtonTest extends JApplet implements ItemListener
   {
   int x=0,
       y=0,
       sum=0,
       a=0,
       b=0,
       c=0;
   Container ctp=getContentPane();
   JTextField tf=new JTextField(10);
   JLabel lb=new JLabel("总数:");
   JCheckBox cb;
   JRadioButton rb1,
                rb2,
                rb3;
   ButtonGroup bg=new ButtonGroup();
   //初始化小程序
   public void init()
     {
     //设置界面布局风格
     ctp.setLayout(new FlowLayout());
     cb=new JCheckBox("帽子10",false);
     cb.addItemListener(this);
     ctp.add(cb);
     cb=new JCheckBox("校服80",false);
     cb.addItemListener(this);
     ctp.add(cb);
     cb=new JCheckBox("手套20",false);
     cb.addItemListener(this);
     ctp.add(cb);
     rb1=new JRadioButton("全班40人");
     rb1.addActionListener(new koListener());
     ctp.add(rb1);
     rb2=new JRadioButton("全班30人");
     rb2.addActionListener(new koListener());
     ctp.add(rb2);
     rb3=new JRadioButton("全班20人");
     rb3.addActionListener(new koListener());
     ctp.add(rb3);
     bg.add(rb1);
     bg.add(rb2);
     bg.add(rb3);
     ctp.add(lb);
     ctp.add(tf);
     }
  //响应用户选择的动作
  public void itemStateChanged(ItemEvent e)
     {
     JCheckBox cbx=(JCheckBox)e.getItem();
     if (cbx.getText()=="帽子10")
	a=10;
     if (cbx.getText()=="校服80")
	b=80;
     if (cbx.getText()=="手套20")
	c=20;
     x=a+b+c;
     sum=x*y;
     tf.setText(String.valueOf(sum)+"元");
     }
  //消息监听
  class koListener implements ActionListener
     {
     public void actionPerformed(ActionEvent e)
	{
         String rbt=e.getActionCommand();
         if (rbt=="全班40人")
            y=40;
         else if (rbt=="全班30人")
            y=30; 
         else
            y=20;
         sum=x*y;
	 tf.setText(String.valueOf(sum)+"元");
	}
     }
}

