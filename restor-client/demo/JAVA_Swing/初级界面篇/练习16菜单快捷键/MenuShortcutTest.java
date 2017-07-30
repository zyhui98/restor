//MenuShortcutTest.java
import java.awt.*;
import java.awt.event.*;
public class MenuShortcutTest extends Frame
   {
    //变量声明
    double a,d,n,sum;
    TextField tf1=new TextField(5),
	      tf2=new TextField(5),
	      tf3=new TextField(5),
	      tf4=new TextField(10);
    Label lb1=new Label("******计算等差/等比级数******"),
	  lb2=new Label("输入初始值："),
	  lb3=new Label("输入公差/公比值:"),
	  lb4=new Label("输入第几项:"),
	  lb5=new Label("输出总和:");
    //菜单快捷键
    MenuShortcut msc1=new MenuShortcut(KeyEvent.VK_A,true),
    			 msc2=new MenuShortcut(KeyEvent.VK_S,false);
    //菜单工具条
    MenuBar bar=new MenuBar();
    Menu mu=new Menu("级数");
    MenuItem sz1=new MenuItem("等差级数",msc1),
			 sz2=new MenuItem("等比级数",msc2);
    public MenuShortcutTest()
	{
	super("菜单快捷键测试");
	setLayout(new FlowLayout());
	setBackground(Color.orange);
	mu.add(sz1);
	mu.add(sz2);
	bar.add(mu);
	setMenuBar(bar);
	add(lb1);
	add(lb2);
	add(tf1);
	add(lb3);
	add(tf2);
	add(lb4);
	add(tf3); 
	add(lb5);
	add(tf4);
	setSize(200,180);
	setVisible(true);
	addWindowListener(new koWindowListener());
	sz1.addActionListener(new koActionListener());
	sz2.addActionListener(new koActionListener());
	}
    //响应关闭窗口
    class koWindowListener extends WindowAdapter
	{
	public void windowClosing(WindowEvent e)
	    {
	     e.getWindow().dispose();
	     System.exit(0);
	    }
	}
    //响应菜单动作
   class koActionListener implements ActionListener
	{
	public void actionPerformed(ActionEvent e)
	    {
	   a=new Double(tf1.getText()).doubleValue();
	   d=new Double(tf2.getText()).doubleValue();
	   n=new Double(tf3.getText()).doubleValue();
	   if (e.getSource()==sz1)
	        {
		tf4.setBackground(Color.yellow);	
	        sum=(2*a+(n-1)*d)*n/2;//计算等差级数的总和
	        }
	   else if (e.getSource()==sz2)
		{
		tf4.setBackground(Color.green);	
	        sum=a*(1-Math.pow(d,n))/(1-d);//计算等比级数的总和
		}
	   tf4.setText(String.valueOf(sum));
	    }
	}
    //主函数
   public static void main(String args[])
	{
	new MenuShortcutTest();
	}
   }
      