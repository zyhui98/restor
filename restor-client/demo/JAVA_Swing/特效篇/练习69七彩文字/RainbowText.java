//RainbowText.java
import java.applet.Applet;
import java.awt.*;

public class RainbowText extends Applet
    implements Runnable
{
    //变量定义	
    String str = null;
    int strlen = 0;
    Thread runner = null;
    char theChars[] = null;
    int charOffsets[] = null;
    Color colors[] = null;
    int phase = 0;
    Image offScreenImage = null;
    Graphics offScreenG = null;
    Font f = null;
    FontMetrics fm = null;

    //初始化小程序
    public void init()
    {
        int i = 20;
        str = getParameter("text");
        if(str == null)
            str = "Museum of Java Applets";
        f = new Font("TimesRoman", 1, 36);
        fm = getFontMetrics(f);
        resize(40 + fm.stringWidth(str), 40);
        setBackground(Color.black);
        strlen = str.length();
        theChars = new char[strlen];
        charOffsets = new int[strlen];
        str.getChars(0, strlen, theChars, 0);
        colors = new Color[strlen];
        for(int j = 0; j < strlen; j++)
        {
            float f1 = (float)j / (float)strlen;
            colors[j] = new Color(Color.HSBtoRGB(f1, 1.0F, 1.0F));
            charOffsets[j] = i;
            i += fm.charWidth(theChars[j]);
        }

        offScreenImage = createImage(size().width, size().height);
        offScreenG = offScreenImage.getGraphics();
        offScreenG.setFont(f);
    }
    //启动小程序
    public void start()
    {
        if(runner == null)
        {
            runner = new Thread(this);
            runner.start();
        }
    }
    //停止小程序
    public void stop()
    {
        if(runner != null)
        {
            runner.stop();
            runner = null;
        }
    }
    //运行小程序
    public void run()
    {
        while(runner != null) 
        {
            try
            {
                Thread.sleep(100L);
            }
            catch(InterruptedException _ex) { }
            repaint();
        }

    }
    //更新屏幕
    public void update(Graphics g)
    {
        offScreenG.setColor(Color.black);
        offScreenG.fillRect(0, 0, size().width, size().height);
        phase++;
        phase %= str.length();
        for(int j = 0; j < strlen; j++)
        {
            int i = charOffsets[j];
            offScreenG.setColor(colors[(phase + j) % strlen]);
            offScreenG.drawChars(theChars, j, 1, i, 30);
        }

        paint(g);
    }
    //画屏函数
    public void paint(Graphics g)
    {
        g.drawImage(offScreenImage, 0, 0, this);
    }
    //构造函数
    public RainbowText()
    {
    }
}
