//ripple.java
import java.applet.Applet;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.PrintStream;

public class ripple extends Applet
    implements Runnable
{
    Image origImg;
    Image backImg;
    Image finImg;
    int w1;
    int h1;
    Graphics backG;
    Graphics finG;
    int phase;
    Thread nick;
    int frameNo;
    int frames;
    int period;
    boolean stopIt;
    boolean borderGap;
    boolean imLoaded;
    
    //程序的初始化和原始图片的导入
    public void init()
    {
        setBackground(Color.white);
        origImg = getImage(getDocumentBase(), getParameter("image"));
        borderGap = "on".equals(getParameter("bordergap"));
        w1 = origImg.getWidth(this);
        h1 = origImg.getHeight(this);
        if(h1 > 0 && w1 > 0)
            stopIt = false;
        period = Integer.parseInt(getParameter("period"));
        frames = Integer.parseInt(getParameter("frames"));
    }

    public void start()
    {
        if(nick == null)
        {
            nick = new Thread(this);
            nick.start();
        }
    }

    public void stop()
    {
        if(nick != null)
        {
            //nick.stop();
            nick = null;
        }
    }
    //对图片进行更新
    public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1)
    {
        boolean flag = true;
        if((i & 0x2) > 0)
            h1 = i1;
        if((i & 0x1) > 0)
            w1 = l;
        if((i & 0x20) > 0)
            imLoaded = true;
        if(imLoaded && w1 > 0 && h1 > 0)
        {
            stopIt = false;
            flag = false;
        }
        return flag;
    }
    //对图片进行旋转
    public void rotateImage(Graphics g)
    {
        shearX(g);
        shearY(g);
    }
    //对图片水平方向进行旋转
    public void shearX(Graphics g)
    {
        for(int i = 0; i < h1; i++)
        {
            double d = (double)(period >> 1) * Math.sin((double)i / (double)period + (6.2831853071795862D * (double)phase) / (double)frames);
            g.copyArea(0, i, w1, 1, (int)d, 0);
            if(borderGap)
            {
                g.drawLine((int)d, i, 0, i);
                g.drawLine((int)d + w1, i, w1, i);
            }
        }

    }
    //对图片竖直方向进行旋转
    public void shearY(Graphics g)
    {
        for(int i = 0; i < w1; i++)
        {
            double d = (double)(period >> 1) * Math.sin((double)i / (double)period + (6.2831853071795862D * (double)phase) / (double)frames);
            g.copyArea(i, 0, 1, h1, 0, (int)d);
            if(borderGap)
            {
                g.drawLine(i, (int)d, i, 0);
                g.drawLine(i, (int)d + h1, i, h1);
            }
        }

    }

    public void run()
    {
        while(nick != null) 
        {
            try
            {
                Thread.sleep(50L);
            }
            catch(InterruptedException _ex) { }
            frameNo = (frameNo + 1) % frames;
            repaint();
        }
    }

    public void update(Graphics g)
    {
        paint(g);
    }
    //更新窗口
    public void paint(Graphics g)
    {
        if(!stopIt)
        {
            stopIt = true;
            createIt();
        }
        if(finImg != null)
        {
            g.drawImage(finImg, 0, -frameNo * h1, this);
            g.drawImage(finImg, 0, (frames - frameNo) * h1, this);
            return;
        } else
        {
            g.drawImage(origImg, 0, 0, this);
            return;
        }
    }
    //生成新图片
    public void createIt()
    {
        backImg = createImage(w1, h1);
        backG = backImg.getGraphics();
        finImg = createImage(w1, frames * h1);
        finG = finImg.getGraphics();
        backG.setColor(Color.white);
        for(phase = 0; phase < frames; phase++)
        {
            backG.drawImage(origImg, 0, 0, this);
            rotateImage(backG);
            finG.drawImage(backImg, 0, phase * h1, this);
        }

    }
    //构造函数
    public ripple()
    {
        frames = 12;
        period = 50;
        stopIt = true;
        borderGap = true;
        imLoaded = false;
    }
}
