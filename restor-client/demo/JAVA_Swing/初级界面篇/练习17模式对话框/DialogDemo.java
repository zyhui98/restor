//DialogDemo.java
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
//派生类
class dlg extends Dialog 
    {
    Button bt=new Button("关闭");
    //构造函数
    dlg(Frame fe,String str)
	{
	super(fe,str,true);
	setLayout(new FlowLayout());
	setSize(200,180);
	add(bt);
	bt.addActionListener(new ko1ActionListener());
	}
    //关闭对话框
    class ko1ActionListener implements ActionListener
	{
	public void actionPerformed(ActionEvent e)
	   {
	   setVisible(false);
	   }
	}
    }
//主类DialogDemo
public class DialogDemo extends Frame
   {
    //变量定义
    Frame fe;
    Menu mu=new Menu("各种颜色的子对话框");
    MenuBar bar=new MenuBar();
    MenuItem i1,i2,i3,i4;
    //构造函数
    public DialogDemo()
	{
	super("主对话框");
	setLayout(new FlowLayout());
	mu.add(i1=new MenuItem("红色..."));
	mu.add(i2=new MenuItem("绿色..."));
        mu.add(i3=new MenuItem("蓝色..."));
	mu.add(new MenuItem("-"));
	mu.add(i4=new MenuItem("退出"));
	bar.add(mu);
	setMenuBar(bar);
	setSize(500,400);
	setVisible(true);
	i1.addActionListener(new ko2ActionListener());
	i2.addActionListener(new ko2ActionListener());
	i3.addActionListener(new ko2ActionListener());
	i4.addActionListener(new ko2ActionListener());
	addWindowListener(new koWindowListener());
	}
    //事件监听函数
    class ko2ActionListener implements ActionListener
	{
	Frame fe=new Frame();
	public void actionPerformed(ActionEvent e)
	   {
	    String ko=e.getActionCommand();
	    if (ko.equals("红色..."))
		{
		dlg d=new dlg(fe,"红色的子对话框");
	        d.setBackground(Color.red);
		d.setVisible(true);
		}
	    else if (ko.equals("绿色..."))
		{
		dlg d=new dlg(fe,"绿色的子对话框");
	        d.setBackground(Color.green);
		d.setVisible(true);
		}
	    else if (ko.equals("蓝色..."))
		{
		dlg d=new dlg(fe,"蓝色的子对话框");
	        d.setBackground(Color.blue);
		d.setVisible(true);
		}
	    else if (ko.equals("退出"))
		{
		dispose();
	        System.exit(0);
		}
	   }
	}
    //关闭窗口
    class koWindowListener extends WindowAdapter
	{
	public void windowClosing(WindowEvent e)
	    {
	     dispose();
	     System.exit(0);
	    }
	}
   //主函数
   public static void main(String args[])
	{
	Frame fe=new Frame();
	dlg k=new dlg(fe,"最初的对话框");
	k.setVisible(true);
	DialogDemo ko=new DialogDemo();
	}
   }
