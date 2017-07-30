//TipButtons .java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TipButtons extends JPanel {
  //变量定义
  static JFrame myFrame;
  protected JLabel label;
  
  //构造函数
  public TipButtons(){
    label = new JLabel ("Hello World!");
    label.setOpaque(true);
    JButton hello = new JButton("Hello");
    hello.setMnemonic('h');
    hello.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
      label.setText("Hello World!");
      }
      });
    //Set the ToolTip for the hello button
    hello.setToolTipText("Select to change label to Hello World");
    
    JButton bye = new JButton("Bye");
    bye.setMnemonic('b');
    bye.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
      label.setText("Good Bye World!");
      }
      });
    //为按钮设置工具提示
    bye.setToolTipText("Select to change label to Good Bye World");
    
    add(bye);
    add(hello);
    add(label);
  }
  
  //主函数
  public static void main(String args[]){
    //生成界面
    myFrame = new JFrame("有工具提示的按钮");
    TipButtons tb = new TipButtons();
    myFrame.getContentPane().add("Center",tb);
    myFrame.setSize(500,200);    
    myFrame.setBackground(Color.orange);
    //监听窗口事件
    myFrame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e)
       	{
       		System.exit(0);
       	}
      }
      );
    myFrame.setVisible(true);
  }
}
