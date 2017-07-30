//CardLayoutDemo.java
import java.awt.*;
import java.awt.event.*;

public class CardLayoutDemo extends Frame
{
	//包含四个功能按钮的Panel的定义和创建
	Panel pnlCommandArea=new Panel();
	//显示功能Panel的定义和创建
	Panel pnlDisplayArea=new Panel();
	//CardLayout布局管理器的创建
	CardLayout cardlayout1=new CardLayout();
	//四个功能按钮的定义和创建
	Button btnFirst=new Button("第一个");
	Button btnPrevious=new Button("前一个");	
	Button btnNext=new Button("后一个");
	Button btnLast=new Button("最后一个");
	
	//框架窗体的构造方法
	public CardLayoutDemo()
	{
		super("卡片布局管理器");
		
		//四个功能按钮的颜色设置	
		btnFirst.setBackground(Color.white);
		btnPrevious.setBackground(Color.white);		
		btnNext.setBackground(Color.white);		
		btnLast.setBackground(Color.white);
		
		//设置Frame的布局管理器为BorderLayout
		this.setLayout(new BorderLayout());
		//把两个Panel加入到布局管理器中
		this.add( pnlCommandArea, BorderLayout.NORTH);
		this.add( pnlDisplayArea, BorderLayout.CENTER);

		//把显示功能区域Panel的布局管理器设置为CardLayout
		pnlDisplayArea.setLayout(cardlayout1);
		//创建第一个显示Panel
		Panel pnlFirst=new Panel();		
		pnlFirst.setBackground(Color.yellow);
		pnlFirst.setForeground(Color.blue);
		pnlDisplayArea.add("first",pnlFirst);
		pnlFirst.add(new Label("这是第一张卡片") );
		//创建第二个显示Panel
		Panel pnlSecond=new Panel();		
		pnlSecond.setBackground(Color.pink);
		pnlSecond.setForeground(Color.blue);
		pnlDisplayArea.add("second",pnlSecond);
		pnlSecond.add(new Label("这是第二张卡片") );
		//创建第三个显示Panel
		Panel pnlThird=new Panel();		
		pnlThird.setBackground(Color.orange);
		pnlThird.setForeground(Color.blue);
		pnlDisplayArea.add("third",pnlThird);
		pnlThird.add(new Label("这是第三张卡片") );
		//创建第四个显示Panel
		Panel pnlFourth=new Panel();		
		pnlFourth.setBackground(Color.green);
		pnlFourth.setForeground(Color.blue);
		pnlDisplayArea.add("fourth",pnlFourth);
		pnlFourth.add(new Label("这是第四张卡片") );

		//为四个功能按钮设置事件监听器
		btnFirst.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
            {
                processAction(e);
            }
		});				
		btnPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
            {
                processAction(e);
            }
		});	
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
            {
                processAction(e);
            }
		});
		btnLast.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
            {
                processAction(e);
            }
		});
		//把四个功能按钮加入到Panel
		pnlCommandArea.add( btnFirst );
		pnlCommandArea.add( btnPrevious );
		pnlCommandArea.add( btnNext );
		pnlCommandArea.add( btnLast );
	}
	
	//程序的入口方法
	public static void main( String[] args )
	{
		//创建框架窗体的实例
		CardLayoutDemo frmCardLayout = new CardLayoutDemo();
		
		//设置框架窗体的事件监听(关闭窗体事件)
		frmCardLayout.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				//正常退出Java虚拟机
				System.exit(0);
			}
		});

		//显示框架窗体
		frmCardLayout.pack();
		frmCardLayout.show();
	}
	//设置框架窗体的大小为300×300
	public Dimension getPreferredSize()
	{
		return new Dimension(300,300);
	}
	//处理按钮的事件
	private void processAction(ActionEvent e)
	{
		//获取事件源（用户选择是哪个按钮）
		Button btnEvent=(Button)e.getSource();
		
		if( btnEvent.equals(btnFirst))
			cardlayout1.first( pnlDisplayArea );
		else if( btnEvent.equals(btnLast))
			cardlayout1.last( pnlDisplayArea );
		else if( btnEvent.equals(btnPrevious))
			cardlayout1.previous( pnlDisplayArea );
		else if( btnEvent.equals(btnNext))
			cardlayout1.next( pnlDisplayArea );		
	}
}