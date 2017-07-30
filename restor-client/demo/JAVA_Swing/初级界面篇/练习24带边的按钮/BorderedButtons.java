//BorderedButtons.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class BorderedButtons extends JPanel {
  //变量定义
  static JFrame myFrame;
  protected JLabel label;
  JPopupMenu pm;
  
  //构造函数
  public BorderedButtons(){
    label = new JLabel ("Hello World!");
    label.setBorder(new EtchedBorder());
    
    JButton hello = new JButton("Hello");    
    hello.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
      label.setText("Hello World!");
      }
      });
          
    //将图片设置为按钮的边界
    Icon icon = new ImageIcon ("java.jpg");
    hello.setBorder(new MatteBorder(10, 10, 10, 10, icon));
     
    //制作按钮
    JButton bye = new JButton("Bye");
    bye.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
      label.setText("Good Bye World!");
      }
      });
    //设置背景颜色
    bye.setBackground (SystemColor.control);
    //设置按钮的边界是绿色的直线
    bye.setBorder(new LineBorder(Color.green));
    
    add(bye);
    add(hello);
    add(label);
    
  }
  //主函数
  public static void main(String args[]){
    myFrame = new JFrame("带有边界的按钮");
    //实例化边界按钮类
    BorderedButtons jt = new BorderedButtons();
    myFrame.getContentPane().add("Center",jt);
    myFrame.setSize(500,200);    
    myFrame.setBackground(Color.orange);    
    //监听窗口消息
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
