//imagefader.java
import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.net.URL;

public class imagefader extends Applet
    implements Runnable
{
    //变量声明
    protected MediaTracker tracker = null;
    private int sleeptime = 0;
    private int delay = 0;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int bred = 0;
    private int bgreen = 0;
    private int bblue = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int dx = 0;
    private int dx2 = 0;
    private int sleepcount = 0;
    private int sel = 0;
    private Double step = null;
    private Thread program_thread = null;
    private int maxitems = 0;
    private Graphics g = null;
    private Graphics g2 = null;
    private Image bufferscreen = null;
    private Image bufferimage = null;
    private Dimension d = null;
    AppletContext appletcontrol = null;
    private String urls[] = null;
    int pixels[] = null;
    private Image images[] = null;
    int imagepixels[][] = null;
    private String target = null;
    //初始化小程序 
    public void init()
    {
        
        super.init();
        setLayout(null);
        appletcontrol = getAppletContext();
        prepareVariables();
        pixels = new int[d.width * d.height + d.width * 10];
    
    }
    //传递事件句柄
    public boolean handleEvent(Event event)
    {
    
        return super.handleEvent(event);
    
    }

    //准备变量的函数
    public void prepareVariables()
    {
    
        sleeptime = getIntegerParameter("sleeptime", 10);
        delay = getIntegerParameter("delay", 10);
        String s = "";
        maxitems = 0;
        while(s != null) 
        {
     
            s = getParameter("bitmap" + maxitems);
            if(s != null)
                maxitems++;
     
        }

        target = getParameter("target");
        step = Double.valueOf(getParameter("step"));
        resize(getIntegerParameter("width", 10), getIntegerParameter("height", 10));
        d = size();
        bufferscreen = createImage(d.width, d.height);
        urls = new String[maxitems];
        images = new Image[maxitems];
        imagepixels = new int[maxitems][d.width * d.height];
        pixels = new int[d.width * d.height];
        tracker = new MediaTracker(this);
        for(i = 0; i < maxitems; i++)
        {
     
            String s1 = String.valueOf(i);
            String s2 = getParameter("bitmap" + s1);
            urls[i] = getParameter("url" + s1);
            showStatus("Loading image: " + i);
            images[i] = getImage(getDocumentBase(), s2);
            tracker.addImage(images[i], i);
            try
            {
     
                tracker.waitForID(i);
     
            }
            catch(InterruptedException _ex) { }
            showStatus("Image " + i + " loaded.");
            if(images[i].getHeight(this) != d.height || images[i].getWidth(this) != d.width)
            {
     
                showStatus("Image does not fit. Resizing...");
                images[i] = resizeImage(images[i], d.width, d.height);
                showStatus("Resizing ready.");
     
            }
            PixelGrabber pixelgrabber = new PixelGrabber(images[i], 0, 0, d.width, d.height, pixels, 0, d.width);
            try
            {
     
                pixelgrabber.grabPixels();
     
            }
            catch(InterruptedException _ex) { }
            for(j = 0; j < d.width * d.height; j++)
                imagepixels[i][j] = pixels[j];

        }

        i = 0;
        j = 0;
        k = 0;
        bufferscreen.getGraphics();
        getGraphics();
    
    }
    //改变图片尺寸
    public Image resizeImage(Image image, int l, int i1)
    {
    
        int j1 = image.getWidth(this);
        int k1 = image.getHeight(this);
        int ai[] = new int[j1 * k1];
        PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, j1, k1, ai, 0, j1);
        try
        {
    
            pixelgrabber.grabPixels();
    
        }
        catch(InterruptedException _ex) { }
        int ai1[] = new int[l * i1];
        for(int l1 = 0; l1 < l; l1++)
        {
    
            for(int i2 = 0; i2 < i1; i2++)
                ai1[l1 + i2 * l] = ai[(l1 * j1) / l + ((i2 * k1) / i1) * j1];

        }

        Image image1 = createImage(new MemoryImageSource(l, i1, ai1, 0, l));
        return image1;
    
    }

    //得到整数类型的参数
    public int getIntegerParameter(String s, int l)
    {
    
        String s1 = getParameter(s);
        int i1;
        try
        {
    
            i1 = Integer.parseInt(s1, l);
    
        }
        catch(NumberFormatException _ex)
        {
    
            return 0;
    
        }
        return i1;
    
    }
    //响应鼠标进入
    public boolean mouseEnter(Event event, int l, int i1)
    {
  
            Container container = getParent();
            for(boolean flag = false; container != null && !flag; container = container.getParent())
                if(container instanceof Frame)
                {
    
                    ((Frame)container).setCursor(12);
                    flag = true;
    
                }

 
        return true;
    
    }
    //响应鼠标退出
    public boolean mouseExit(Event event, int l, int i1)
    {
            
            Container container = getParent();
            for(boolean flag = false; container != null && !flag; container = container.getParent())
                if(container instanceof Frame)
                {
    
                    ((Frame)container).setCursor(0);
                    flag = true;
    
                }

        
        return true;
    }
    //响应鼠标按下
    public boolean mouseDown(Event event, int l, int i1)
    {
            
            URL url;
            try
            {
    
                url = new URL(urls[sel]);
    
            }
            catch(MalformedURLException _ex)
            {
    
                showStatus("Invalid URL");
                return true;
    
            }
            if(target != null)
            {
    
                if(target.length() > 0)
                    appletcontrol.showDocument(url, target);
                else
                    appletcontrol.showDocument(url);
    
            }
            else
            {
    
                appletcontrol.showDocument(url);
    
            }
        
        return true;
    
    }
    //制作图片效果
    public Image fadein(int l, int i1, double d1)
    {
    
        int l1 = (int)(d1 * 100D);
        l1 = Math.min(l1, 99);
        for(int j1 = 0; j1 < d.height; j1++)
        {
    
            for(int k1 = 0; k1 < d.width; k1++)
            {
    
                red = (((imagepixels[i1][j1 * d.width + k1] & 0xff0000) >> 16) * l1) / 100;
                green = (((imagepixels[i1][j1 * d.width + k1] & 0xff00) >> 8) * l1) / 100;
                blue = ((imagepixels[i1][j1 * d.width + k1] & 0xff) * l1) / 100;
                bred = (((imagepixels[l][j1 * d.width + k1] & 0xff0000) >> 16) * (100 - l1)) / 100;
                bgreen = (((imagepixels[l][j1 * d.width + k1] & 0xff00) >> 8) * (100 - l1)) / 100;
                bblue = ((imagepixels[l][j1 * d.width + k1] & 0xff) * (100 - l1)) / 100;
                pixels[j1 * d.width + k1] = (red << 16) + (green << 8) + blue + 0xff000000 + (bred << 16) + (bgreen << 8) + bblue;
    
            }

        }

        Image image = createImage(new MemoryImageSource(d.width, d.height, pixels, 0, d.width));
        return image;
    
    }
    //画屏函数
    public void paint(Graphics g1)
    {
    
        if(g1 != null && bufferimage != null)
            g1.drawImage(bufferimage, 0, 0, this);
    
    }
    //更新屏幕函数
    public void update(Graphics g1)
    {
    
        paint(g1);
    
    }
    //开始小程序
    public void start()
    {
    
        if(program_thread == null)
        {
    
            program_thread = new Thread(this);
            program_thread.start();
    
        }
    
    }
    //停止小程序
    public void stop()
    {
    
        if(program_thread != null && program_thread.isAlive())
            program_thread.stop();
        program_thread = null;
    
    }
    //运行小程序
    public void run()
    {
 
            do
            {
 
                double d1 = 0.0D;
                bufferimage = fadein(0, 1, 0.0D);
                paint(getGraphics());
                try
                {
 
                    Thread.sleep(sleeptime);
 
                }
                catch(InterruptedException _ex) { }
                for(i = 0; i < maxitems - 1; i++)
                {
 
                    for(double d2 = 0.0D; d2 < 1.0D + step.doubleValue(); d2 += step.doubleValue())
                    {
 
                        bufferimage = fadein(i, i + 1, d2);
                        try
                        {
 
                            Thread.sleep(delay);
 
                        }
                        catch(InterruptedException _ex) { }
                        paint(getGraphics());
 
                    }

                    sel = i + 1;
                    try
                    {
 
                        Thread.sleep(sleeptime);
 
                    }
                    catch(InterruptedException _ex) 
                    { 

                    }
 
                }

                for(double d3 = 0.0D; d3 < 1.0D + step.doubleValue(); d3 += step.doubleValue())
                {
                    bufferimage = fadein(maxitems - 1, 0, d3);
                    try
                    {

                        Thread.sleep(delay);

                    }
                    catch(InterruptedException _ex) 
                    { 

                    }
                    paint(getGraphics());

                }

                sel = 0;
                try
                {

                    Thread.sleep(sleeptime);

                }
                catch(InterruptedException _ex) 
                { 

                }

            }
            while(true);

    }
    //构造函数
    public imagefader()
    {

        sleeptime = 5;
        delay = 20;
        maxitems = 8;

    }

}
