//SliderExample.java
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class SliderExample extends JPanel{   
  //变量定义
  JLabel slider5Value;
  static JFrame myFrame;
  //构造函数
  public SliderExample() {
    Box horizBox = Box.createHorizontalBox();
    
    //制作第一个滑杆
    JSlider slider1 = new JSlider (JSlider.VERTICAL, 0, 50, 25);
    slider1.setPaintTicks(true);    
    slider1.setMajorTickSpacing(10);
    slider1.setMinorTickSpacing(2);          
    slider1.setSnapToTicks(true);
    slider1.setBackground(Color.orange);
    horizBox.add(slider1);
    horizBox.add(horizBox.createHorizontalStrut(15));
    
    //制作第二个滑杆
    JSlider slider2 = new JSlider (JSlider.VERTICAL, 0, 50,25);
    slider2.setPaintTicks(true);    
    slider2.setMinorTickSpacing(5); 
    slider2.setBackground(Color.green);     
    horizBox.add(slider2);
    horizBox.add(horizBox.createHorizontalStrut(15));
    
    //制作第三个滑杆
    JSlider slider3 = new JSlider (JSlider.VERTICAL, 0, 50,25);
    slider3.setPaintTicks(true);    
    slider3.setMajorTickSpacing(10); 
    slider3.setBackground(Color.yellow);   
    horizBox.add(slider3);
    horizBox.add(horizBox.createHorizontalStrut(15));
    
    //制作第四个滑杆    
    JSlider slider4 =  new JSlider (JSlider.VERTICAL, 0, 50,25);
    slider4.setBorder(LineBorder.createBlackLineBorder());
    slider4.setBackground(Color.blue);
    horizBox.add(slider4);
    horizBox.add(horizBox.createHorizontalStrut(15));
    
    //制作第五个滑杆
    JSlider slider5 =  new JSlider (JSlider.VERTICAL, 0, 50,25);
    slider5.setBorder(LineBorder.createBlackLineBorder());
    slider5.setMajorTickSpacing(10);      
    slider5.setPaintLabels(true);
    slider5.setBackground(Color.pink);
    horizBox.add(slider5);
    horizBox.add(horizBox.createHorizontalStrut(15));

    //设置第五个滑杆的值    
    slider5Value = new JLabel("第五个滑杆的值 = 25");
    horizBox.add(slider5Value);
    //响应用户选择
    slider5.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        slider5Value.setText("第五个滑杆的值 = "+((JSlider)event.getSource()).getValue());
      }
    });
                              
    
    setLayout(new BorderLayout());
    add(horizBox,"Center");
  }

  //主函数
  public static void main(String args[]){
    myFrame = new JFrame("滑杆");
    SliderExample sliderExample = new SliderExample();
    myFrame.getContentPane().add("Center",sliderExample);
    myFrame.setSize(350,300);
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
