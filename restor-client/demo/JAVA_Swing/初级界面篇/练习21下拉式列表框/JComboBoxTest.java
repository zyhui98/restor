//JComboBoxTest.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JComboBoxTest extends JApplet implements ItemListener
   {
   //变量定义
   Container ctp=getContentPane();
   JTextField tf1=new JTextField(5),
	      tf2=new JTextField(5);
   JLabel lb1=new JLabel("单价:"),
	  lb2=new JLabel("库存量:");
   String obj[]={"大米","饮料","面粉","酱油","口香糖"},
          price[]={"12","118","29","24","47"},
          num[]={"232","45","405","12","49"};
   JComboBox cbx=new JComboBox();
   //初始化小程序
   public void init()
     {
     //设置页面布局风格
     ctp.setLayout(new FlowLayout());
     for (int n=0;n<obj.length;n++)
         cbx.addItem(obj[n]);
     ctp.add(cbx);
     //添加消息监听
     cbx.addItemListener(this);
     ctp.add(lb1);
     ctp.add(tf1);
     ctp.add(lb2);
     ctp.add(tf2);
     }
  //响应用户操作
  public void itemStateChanged(ItemEvent e)
     {
     int x=0,y;
     String ko=(String)e.getItem();
     for (y=0;y<obj.length;y++)
        if (ko==obj[y])
	    x=cbx.getSelectedIndex();
     //设置价格
     tf1.setText(price[x]);
     //设置数量
     tf2.setText(num[x]);
     }
}

