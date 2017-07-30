//ListComboExample.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ListComboExample extends JPanel{
  static JFrame myFrame;
  
  public ListComboExample(){
  	//采用网格布局管理器
    setLayout(new GridLayout(2,2));
    //实例化JList
    JList list = new JList(new ListModelExample());
    list.setVisibleRowCount(4);
    //滚动面板
    JScrollPane pane = new JScrollPane();
    pane.setViewportView(list);
    add(pane);
    //组合框
    JComboBox combobox = new JComboBox(new ComboModelExample());
    add(combobox);
  }
  
  //主函数，应用程序入口
  public static void main(String args[]){
    myFrame = new JFrame("列表框和组合框实例");
    ListComboExample jt = new ListComboExample();
    myFrame.getContentPane().add("Center",jt);
    myFrame.setSize(200,200);
    //添加消息映射
    myFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {System.exit(0);}
    });
    //设为可见
    myFrame.setVisible(true);
  }
  //列表框
  class ListModelExample extends AbstractListModel{
    String values[] = {"张三","李四","王五","周六","赵七","孙八"};
    public Object getElementAt(int index){
      return values[index];
    }
    
    public int getSize(){
      return values.length;
    }
  }
  //组合框
  class ComboModelExample extends ListModelExample
	 implements ComboBoxModel{
    Object item;
    public void setSelectedItem(Object anItem){
      item = anItem;
    }
    public Object getSelectedItem(){
      return item;
    }

  }
}
