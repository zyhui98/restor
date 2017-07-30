//magnifier.java
import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.PrintStream;
import java.net.MalformedURLException; 
import java.net.URL;

public class magnifier extends Applet
{
    //变量定义
    Image resultimage = null;
    Image im = null;
    Image bgi = null;
    int xfrom = 0;
    int yfrom = 0;
    int spot = 0;
    int spot2 = 0;
    PixelGrabber pg = null;
    int pixs[] = null;
    int pixr[] = null;
    int w = 0;
    int h = 0;
    int spot2spot = 0;
    MemoryImageSource ms = null;
    Graphics bgg = null;
    //初始化小程序 
    public void init()
    {
        
        im = getImage(getCodeBase(), getParameter("IMAGE"));
        MediaTracker mediatracker = new MediaTracker(this);
        mediatracker.addImage(im, 0);
        showStatus("Loading image, please wait...");
        try
        {
        	
            mediatracker.waitForAll();
        
        }
        catch(InterruptedException interruptedexception)
        {
        	
            System.out.println("InterruptedException:" + interruptedexception);
    
        }
        w = im.getWidth(this);
        h = im.getHeight(this);
        String s = getParameter("SIZE");
        if(s != null)
            try
            {
    
                spot = Integer.parseInt(s);
    
            }
            catch(NumberFormatException numberformatexception)
            {
    
                System.out.println("Check SIZE parameter, NumberFormatException " + numberformatexception);
    
            }
        spot2 = spot << 1;
        spot2spot = spot * spot;
        resultimage = createImage(spot2, spot2);
        pixs = new int[w * h];
        pixr = new int[spot2 * spot2];
        pg = new PixelGrabber(im, 0, 0, w, h, pixs, 0, w);
        try
        {
    
            pg.grabPixels();
    
        }
        catch(InterruptedException interruptedexception1)
        {
    
            System.out.println("InterruptedException in PixelGrabber"+ interruptedexception1);
    
        }
        ms = new MemoryImageSource(spot2, spot2, pixr, 0, spot2);
        bgi = createImage(w, h);
        bgg = bgi.getGraphics();
        xfrom = (int)(Math.random() * (double)(w - spot2));
        yfrom = (int)(Math.random() * (double)(w - spot2));
        preparepaint();
    
    }
    //刷新屏幕
    public void update(Graphics g)
    {
    	
        paint(g);
        
    }
    //准备画屏函数
    public void preparepaint()
    {
    	
        if(xfrom < 0)
            xfrom = 0;
        if(xfrom > w - spot2)
            xfrom = w - spot2;
        if(yfrom < 0)
            yfrom = 0;
        if(yfrom > h - spot2)
            yfrom = h - spot2;
        for(int j = -spot; j < spot; j += 2)
        {
        	
            int l = j * spot2 + spot + (spot2spot << 1);
            int k = j * j;
            int i1 = ((j >> 1) + spot + yfrom) * w + spot + xfrom;
            int j1 = (int)Math.sqrt(spot2spot - k);
            for(int i = -j1; i < j1; i += 2)
                pixr[l + i + spot2] = pixr[l + i + 1 + spot2] = pixr[l + i] = pixr[l + i + 1] = pixs[i1 + (i >> 1)];

        }

        resultimage = createImage(ms);
        
    }
    //画屏函数
    public void paint(Graphics g)
    {
        
        bgg.drawImage(im, 0, 0, this);
        bgg.drawImage(resultimage, xfrom, yfrom, this);
        g.drawImage(bgi, 0, 0, this);
    
    }
    //响应鼠标进入
    public boolean mouseEnter(Event event, int i, int j)
    {
    
        showStatus(getParameter("LINK"));
        return true;
    
    }
    //响应鼠标离开
    public boolean mouseExit(Event event, int i, int j)
    {
    
        showStatus("");
        return true;
    
    }
    //响应鼠标弹开
    public boolean mouseUp(Event event, int i, int j)
    {
    
        String s = getParameter("LINK");
        if(s != null)
            try
            {
    
                URL url = new URL(getDocumentBase(), s);
                getAppletContext().showDocument(url, "_self");
    
            }
            catch(MalformedURLException malformedurlexception)
            {
    
                System.out.println("Please, check LINK parameter"+ malformedurlexception);
    
            }
        return true;
    
    }
    //响应鼠标移动
    public boolean mouseMove(Event event, int i, int j)
    {
    
        xfrom = i - spot;
        yfrom = j - spot;
        preparepaint();
        repaint();
        return true;
    
    }
    //构造函数
    public magnifier()
    {
    
        spot = 30;
 
    }
}