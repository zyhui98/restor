//Neon.java
import java.applet.Applet;
import java.awt.*;

public class Neon extends Applet
    implements Runnable
{  
    //变量声明
    Image mAge[] = null;
    int iMagNdx = 0;
    Thread endit = null;
    //初始化小程序
    public void init()
    {
    	resize(300, 250);
	setBackground(Color.orange);
    }
    //画屏函数
    public void Paint(Graphics g)
    {
        update(g);
    }
    //刷屏函数
    public void update(Graphics g)
    {
        if(mAge[iMagNdx] == null)
            g.drawString("Error loading pic", 0, 170);
        g.drawImage(mAge[iMagNdx], 0, 0, this);
    }
    //启动小程序
    public void start()
    {
        if(endit == null)
        {
            endit = new Thread(this);
            endit.start();
        }
    }
    //停止小程序
    public void stop()
    {
        endit = null;
    }
    //运行小程序
    public void run()
    {
    	mAge = new Image[2];
        String s = getParameter("picture1");
        String s1 = getParameter("picture2");
        mAge[0] = getImage(getDocumentBase(), s);
        mAge[1] = getImage(getDocumentBase(), s1);
        do
        {
            //刷新屏幕	 
            repaint();
            iMagNdx = iMagNdx != 0 ? 0 : 1;
            try
            {
            	//线程睡眠
                Thread.sleep((int)(Math.random() * 500D));
            }
            catch(InterruptedException _ex) 
            {
            }
        }
        while(true);        
    }
}