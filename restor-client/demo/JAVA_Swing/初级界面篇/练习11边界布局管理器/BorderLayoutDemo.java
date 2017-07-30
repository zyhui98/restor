//BorderLayoutDemo.java
import java.awt.*;
import java.awt.event.*;

public class BorderLayoutDemo extends Frame
{
	public BorderLayoutDemo()
	{
		//设置框架窗口的布局管理器为BorderLayoutDemo
		this.setLayout( new BorderLayout()); 
		
		//向框架窗口添加5个Button
		this.add( new Button("南"), BorderLayout.SOUTH);
		this.add( new Button("北") ,BorderLayout.NORTH);
		this.add( new Button("中间") ,BorderLayout.CENTER);
		this.add( new Button("西") ,BorderLayout.WEST);
		this.add( new Button("东") ,BorderLayout.EAST);
		
	}
	public static void main( String[] args )
	{
		BorderLayoutDemo frmBorderLayout=new BorderLayoutDemo();
		
		//设置框架窗体的事件监听(关闭窗体事件)
		frmBorderLayout.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		//显示框架窗体
		frmBorderLayout.pack();
		frmBorderLayout.show();
	}
	//设置框架窗体的大小为300×300
	public Dimension getPreferredSize()
	{
		return new Dimension(300,300);
	}	
}