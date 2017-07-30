//CheckBoxExample.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class CheckBoxExample extends JPanel implements ActionListener,SwingConstants{
  //变量声明
  static JFrame myFrame;
  protected JLabel label;
  JButton theButton;
  
  //构造函数
  public CheckBoxExample(){
    Icon icon = new ImageIcon ("java.jpg");
    theButton = new JButton("My java",icon);  
    theButton.setBackground(Color.green);  
    add (theButton);
    add (new CheckBoxPanel(this));
  }    
  
  //响应用户动作
  public void actionPerformed(ActionEvent ae){
    String action = ae.getActionCommand();
    if (action.equals("文字上对齐")){
      theButton.setVerticalTextPosition(TOP);
      theButton.setHorizontalTextPosition(CENTER);
    }
    else if (action.equals("文字下对齐")){
      theButton.setVerticalTextPosition(BOTTOM);
      theButton.setHorizontalTextPosition(CENTER);
    }
    else if (action.equals("文字右对齐")){
      theButton.setHorizontalTextPosition(RIGHT);
      theButton.setVerticalTextPosition(CENTER);
    }            
    else if (action.equals("文字左对齐")){
      theButton.setHorizontalTextPosition(LEFT);
      theButton.setVerticalTextPosition(CENTER);
    }
    else if (action.equals("文字中心对齐")){
      theButton.setHorizontalTextPosition(CENTER);
      theButton.setVerticalTextPosition(CENTER);
    }
  }

  //主函数
  public static void main(String args[]){
    myFrame = new JFrame("用复选框控制文字对齐");
    CheckBoxExample jt = new CheckBoxExample();
    myFrame.getContentPane().add("Center",jt);
    myFrame.setSize(400,250);      
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
