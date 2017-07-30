//RunningRainbow.java
import java.applet.Applet;
import java.awt.*;

public class RunningRainbow extends Applet
    implements Runnable
{
    //变量定义
    int delay = 0;
    int shift = 0;
    int offset = 0;
    double ss = 0;
    double ar = 0;
    double ag = 0;
    double ab = 0;
    double sr = 0;
    double sg = 0;
    double sb = 0;
    double ar1 = 0;
    String direction = null;
    String method = null;
    boolean isInvert = false;
    boolean isRunning = false;
    boolean isParams = false;
    Thread animatorThread = null;
    Image offImage = null;
    Graphics offGraphics = null;
    //启动小程序
    public void start()
    { 
        if(isRunning)
        { 
            if(animatorThread == null)
                animatorThread = new Thread(this);
            animatorThread.start(); 
        } 
    }
    //得到参数信息
    public String[][] getParameterInfo()
    { 
        String as[][] = {
            { 
                "delay", "Integer", "delay of animation"
            }, {
                "shift", "Integer", "shift of animation"
            }, {
                "method", "Integer", "method of rainbow rendering: gaussian or trapezoid"
            }, {
                "ar", "Double", "mean value of red component"
            }, {
                "ag", "Double", "mean value of green component"
            }, {
                "ab", "Double", "mean value of blue component"
            }, {
                "sr", "Double", "dispersion of red component"
            }, {
                "sg", "Double", "dispersion of green component"
            }, {
                "sb", "Double", "dispersion of blue component"
            }
 
        };
        return as; 
    }
    //停止小程序
    public void stop()
    { 
        animatorThread = null; 
    }
    //构造彩虹的颜色
    public Color cg(int i)
    {    
        for(; i >= size().width; i -= size().width);
        double d = (double)i / (double)size().width;
        double d1;
        if(ar1 != 0.0D && d > 0.5D)
            d1 = d - ar1;
        else
            d1 = d - ar;
        double d2 = d - ag;
        double d3 = d - ab;
        if(sr != 0.0D)
            d1 = Math.exp((-d1 * d1) / sr);
        else
            d1 = 0.0D;
        if(sg != 0.0D)
            d2 = Math.exp((-d2 * d2) / sg);
        else
            d2 = 0.0D;
        if(sb != 0.0D)
            d3 = Math.exp((-d3 * d3) / sb);
        else
            d3 = 0.0D;
        if(isInvert)
            return new Color((float)(1.0D - d1), (float)(1.0D - d2), (float)(1.0D - d3));
        else
            return new Color((float)d1, (float)d2, (float)d3);
    
    }
    //设置彩虹RGB的颜色
    public Color c(int i)
    {
    
        if(i >= size().width)
            i -= size().width;
        double d = ((double)i * 6D) / (double)size().width;
        double d1;
        double d2;
        double d3;
        if(d < 1.0D)
        {
            d1 = 1.0D;
            d2 = d;
            d3 = 0.0D; 
        }
        else
        if(d < 2D)
        { 
            d1 = 2D - d;
            d2 = 1.0D;
            d3 = 0.0D; 
        }
        else
        if(d < 3D)
        { 
            d1 = 0.0D;
            d2 = 1.0D;
            d3 = d - 2D; 
        }
        else
        if(d < 4D)
        { 
            d1 = 0.0D;
            d2 = 4D - d;
            d3 = 1.0D; 
        }
        else
        if(d < 5D)
        { 
            d1 = d - 4D;
            d2 = 0.0D;
            d3 = 1.0D; 
        }
        else
        { 
            d1 = 1.0D;
            d2 = 0.0D;
            d3 = 6D - d; 
        }
        return new Color((float)d1, (float)d2, (float)d3); 
    }
    //响应鼠标按下
    public boolean mouseDown(Event event, int i, int j)
    {
 
        if(isRunning)
        {
 
            isRunning = false;
            stop();
 
        }
        else
        {
 
            isRunning = true;
            start();
 
        }
        return true;
 
    }
    //将字符类型转换成双精度小数类型
    public double parseDouble(String s)
    {
 
        Double double1 = new Double(s);
        return double1.doubleValue();
 
    }

    //得到.HTML文件中的参数
    public void getParams()
    {
 
        isParams = true;
        String s = getParameter("delay");
        int i = s == null ? 100 : Integer.parseInt(s);
        delay = i <= 10 ? 100 : i;
        s = getParameter("shift");
        i = s == null ? 2 : Integer.parseInt(s);
        i = i < 0 || i >= 256 ? 2 : i;
        shift = (size().width * i) / 256;
        direction = getParameter("direction");
        if(direction == null)
            direction = new String("left");
        s = getParameter("invert");
        isInvert = s != null;
        method = getParameter("method");
        if(method == null)
            method = new String("none");
        if(method.compareTo("gaussian") == 0)
        {
 
            ar = getDoubleParam("ar", 0.0D);
            ag = getDoubleParam("ag", 0.5D);
            ab = getDoubleParam("ab", 1.0D);
            ar1 = getDoubleParam("ar1", 0.0D);
            if(getParameter("ss") != null)
            {
 
                ss = getDoubleParam("ss", 0.5D);
                sr = sg = sb = ss;
 
            }
            else
            {
 
                sr = getDoubleParam("sr", 0.5D);
                sg = getDoubleParam("sg", 0.5D);
                sb = getDoubleParam("sb", 0.5D);
 
            }
            sr = (sr * sr) / 2D;
            sg = (sg * sg) / 2D;
            sb = (sb * sb) / 2D;
 
        }
 
    }
    //运行小程序
    public void run()
    {
 
        Thread.currentThread().setPriority(3);
        if(direction.compareTo("none") == 0)
        {
 
            offset = shift;
            repaint();
            return;
 
        }
        long l = System.currentTimeMillis();
        while(Thread.currentThread() == animatorThread) 
        {
 
            repaint();
            try
            {
 
                l += delay;
                Thread.sleep(Math.max(0L, l - System.currentTimeMillis()));
 
            }
            catch(InterruptedException _ex)
            {
                
                return;

            }
            if(direction.compareTo("right") == 0)
            {

                offset -= shift;
                if(offset < 0)
                    offset = size().width;

            }
            else
            if(direction.compareTo("left") == 0)
            {

                offset += shift;
                if(offset >= size().width)
                    offset = 0;

            }

        }

    }
    //构造函数
    public RunningRainbow()
    {

    }
    //创建内存的缓冲区
    public void createBuffer()
    {

        if(offImage == null)
            offImage = createImage(size().width, size().height);
        offGraphics = offImage.getGraphics();
        if(method.compareTo("gaussian") == 0)
        {

            for(int i = 0; i < size().width; i++)
            {

                offGraphics.setColor(cg(i));
                offGraphics.drawLine(i, 0, i, size().height);

            }

            return;

        }
        for(int j = 0; j < size().width; j++)
        {

            offGraphics.setColor(c(j));
            offGraphics.drawLine(j, 0, j, size().height);

        }

    }
    //初始化小程序
    public void init()
    {

        isRunning = true;
        if(!isParams)
            getParams();

    }
    //画屏函数
    public void paint(Graphics g)
    {

        if(offImage != null)
        {

            g.drawImage(offImage, -offset, 0, this);
            g.drawImage(offImage, size().width - offset, 0, this);

        }

    }
    //刷新屏幕函数
    public void update(Graphics g)
    {

        if(offImage == null)
            createBuffer();
        g.drawImage(offImage, -offset, 0, this);
        g.drawImage(offImage, size().width - offset, 0, this);

    }
    //得到字符串和双精度小数作为参数
    public double getDoubleParam(String s, double d)
    {

        String s1 = getParameter(s);
        double d1;
        if(s1 != null)
            d1 = parseDouble(s1);
        else
            d1 = d;
        if(d1 < 0.0D || d1 > 1.0D)
            d1 = d;
        return d1;
    }
}