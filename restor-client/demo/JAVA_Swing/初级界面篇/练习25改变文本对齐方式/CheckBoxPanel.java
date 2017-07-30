//CheckBoxPanel.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckBoxPanel extends JPanel implements SwingConstants{
  public CheckBoxPanel(ActionListener al){
    
    //变量定义
    Box vertBox = Box.createVerticalBox();        
    Box topBox = Box.createHorizontalBox();
    Box middleBox = Box.createHorizontalBox();
    Box bottomBox = Box.createHorizontalBox();
    
    ButtonGroup group = new ButtonGroup();

    //创建复选框
    //上
    JCheckBox north = new JCheckBox("文字上对齐");
    north.addActionListener(al);
    north.setActionCommand("文字上对齐");
    north.setBackground(Color.orange); 
    group.add(north);    
    topBox.add(north);
    //左
    JCheckBox west = new JCheckBox("文字左对齐");
    west.addActionListener(al);
    west.setActionCommand("文字左对齐");
    west.setBackground(Color.orange); 
    group.add(west);	
    middleBox.add(west);
    //中
    JCheckBox center = new JCheckBox("文字中心对齐");
    center.addActionListener(al);
    center.setActionCommand("文字中心对齐");
    center.setBackground(Color.red);
    group.add(center);
    middleBox.add(center);
    //右
    JCheckBox east = new JCheckBox("文字右对齐");
    east.setBackground(Color.orange); 
    east.addActionListener(al);
    east.setActionCommand("文字右对齐");
    //下
    group.add(east);
    middleBox.add(east);
    JCheckBox south = new JCheckBox("文字下对齐");
    south.addActionListener(al);
    south.setActionCommand("文字下对齐");
    south.setBackground(Color.orange); 
    group.add(south);
    bottomBox.add(south);
    //加载复选框
    vertBox.add (topBox);
    vertBox.add (middleBox);
    vertBox.add (bottomBox);
    
    add(vertBox);
  }
}
