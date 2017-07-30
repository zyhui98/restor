//SnowPic.java
import java.applet.Applet;
import java.awt.*;
import java.util.Random;

public class SnowPic extends Applet
    implements Runnable
{
    //变量声明
    Thread mainThread;
    Image offScreen;
    Image gAlc[];
    Random rand;
    int stopFlag;
    int snows;
    int wind;
    int threadSleep;
    int snowSize;
    int snowX[];
    int snowY[];
    long stopTime;
    Dimension dim;
    MediaTracker mt;
    
    //构造函数
    public SnowPic()
    {
        stopTime = 0L;
    }
    //得到一个字符串和一个整数类型的参数
    int getParameter(String s, int i)
    {
        String s1 = getParameter(s);
        return s1 == null ? i : Integer.parseInt(s1);
    }
    //得到一个字符串和三个整数类型的参数
    int getParameter(String s, int i, int j, int k)
    {
        String s1 = getParameter(s);
        if(s1 != null)
        {
            if((i = Integer.parseInt(s1)) > j)
                return j;
            if(i < k)
                return k;
            else
                return i;
        } else
        {
            return i;
        }
    }
    //得到两个字符串的参数
    String getParameter(String s, String s1)
    {
        String s2 = getParameter(s);
        return s2 == null ? s1 : s2;
    }
    //初始化小程序
    public void init()
    {
        rand = new Random();
        dim = getSize();
        offScreen = createImage(dim.width, dim.height);
        snows = getParameter("snows", 100, 500, 0);
        snowSize = getParameter("snowSize", 3, 10, 3);
        threadSleep = getParameter("threadsleep", 80, 1000, 10);
        snowX = new int[snows];
        snowY = new int[snows];
        for(int i = 0; i < snows; i++)
        {
            snowX[i] = rand.nextInt() % (dim.width / 2) + dim.width / 2;
            snowY[i] = rand.nextInt() % (dim.height / 2) + dim.height / 2;
        }

        mt = new MediaTracker(this);
        gAlc = new Image[1];
        gAlc[0] = getImage(getDocumentBase(), getParameter("graphic", "test.gif"));
        mt.addImage(gAlc[0], 0);
        try
        {
            mt.waitForID(0);
        }
        catch(InterruptedException interruptedexception)
        {
            return;
        }
        stopFlag = 0;
    }
    //启动小程序
    public void start()
    {
        if(mainThread == null)
        {
            mainThread = new Thread(this);
            mainThread.start();
        }
    }
    //停止小程序
    public void stop()
    {
        mainThread = null;
    }
    //运行小程序
    public void run()
    {
        while(mainThread != null) 
        {
            try
            {
                Thread.sleep(threadSleep);
            }
            catch(InterruptedException interruptedexception)
            {
                return;
            }
            repaint();
        }
    }
    //生成雪花
    public void drawBackSnow(Graphics g)
    {
        g.setColor(Color.white);
        for(int i = 0; i < snows; i++)
        {
            g.fillOval(snowX[i], snowY[i], snowSize, snowSize);
            snowX[i] += rand.nextInt() % 2 + wind;
            snowY[i] += (rand.nextInt() % 6 + 5) / 5 + 1;
            if(snowX[i] >= dim.width)
                snowX[i] = 0;
            if(snowX[i] < 0)
                snowX[i] = dim.width - 1;
            if(snowY[i] >= dim.height || snowY[i] < 0)
            {
                snowX[i] = Math.abs(rand.nextInt() % dim.width);
                snowY[i] = 0;
            }
        }

        wind = rand.nextInt() % 5 - 2;
    }
    //画屏函数
    public void paint(Graphics g)
    {
        offScreen.getGraphics().setColor(Color.black);
        offScreen.getGraphics().fillRect(0, 0, dim.width, dim.height);
        offScreen.getGraphics().drawImage(gAlc[0], 0, 0, this);
        drawBackSnow(offScreen.getGraphics());
        g.drawImage(offScreen, 0, 0, null);
    }
    //更新屏幕函数
    public void update(Graphics g)
    {
        paint(g);
    }
}