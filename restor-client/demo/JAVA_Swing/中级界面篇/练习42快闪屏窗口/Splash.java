//Splash.java 
//用Window组件来制作快闪屏窗口
import java.awt.*;

public class Splash extends Frame
{
	//frame的构造方法
    public Splash()
    {
    	//获取快闪屏所需的图像
    	Image imgSplash=Toolkit.getDefaultToolkit().getImage("splash.gif");
    	//构造装载Image图像的Panel
    	ImagePane pnlImage=new ImagePane( imgSplash );
    	//创建快闪屏窗口并把图像Panel加入其中
    	Window splashWindow=new Window(this);
    	splashWindow.add(pnlImage,BorderLayout.CENTER);
    	//获取整个屏幕的尺寸大小
    	Dimension scmSize=Toolkit.getDefaultToolkit().getScreenSize();
    	//图像的宽和高
    	int nImageWidth=imgSplash.getWidth(this);
    	int nImageHeight=imgSplash.getHeight(this);
    	//设置快闪屏窗口的大小和居中位置
    	splashWindow.setSize(nImageWidth,nImageHeight);
    	splashWindow.setLocation(scmSize.width/2-nImageWidth/2,scmSize.height/2-nImageHeight/2);
    	//显示快闪屏窗口并置之最前端
    	splashWindow.show();
    	splashWindow.toFront();
    	
    	try
    	{
    		//使当前线程睡眠10秒来模拟应用程序的装载
    		Thread.currentThread().sleep(10000);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();	
    	}    	    	
    	//关闭快闪屏窗体并释放该窗体的资源
		splashWindow.dispose();
	}
	//程序的入口方法
	public static void main( String[] args )
	{
		//创建框架窗体
		Splash frmSplash=new Splash();
		//正常退出Java虚拟机
		System.exit(0);		
	}
}
//封装装载图像的Panel
class ImagePane extends Panel
{
	private Image imgSplash;
	public ImagePane(Image image)
	{
		MediaTracker mt=new MediaTracker(this);
		mt.addImage(image,0);
		try
		{
			mt.waitForID(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		this.imgSplash=image;
		
	}
	//绘制图像
	public void paint(Graphics g)
	{
		g.drawImage(imgSplash,0,0,this);
	}
	//设置图像Panel的大小
	public Dimension getPreferredSize()
	{
		return new Dimension( imgSplash.getWidth(this),imgSplash.getHeight(this));
	}
	
}