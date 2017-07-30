//dynarule.java
import java.applet.Applet;
import java.awt.*;

public class dynarule extends Applet
    implements Runnable
{
    //变量定义
    int xs = 0;
    int ys = 0;
    int size = 0;
    int dy = 0;
    int num = 0;
    Image img = null;
    Color fg = null;
    Color bg = null;
    Color lc = null;
    Color dc = null;
    float am = 0;
    float of = 0;
    float x = 0;
    float dist = 0;
    float speed = 0;
    MediaTracker tracker = null;
    Image buffer_i = null;
    Graphics buffer_g = null;
    Thread kicker = null;
    float Pi2 = 0;
    //初始化小程序
    public void init()
    {
        tracker = new MediaTracker(this);
        xs = size().width;
        ys = size().height;
        String s = getParameter("img");
        img = getImage(getDocumentBase(), s);
        tracker.addImage(img, 0);
        s = getParameter("size");
        size = s != null ? Integer.valueOf(s).intValue() : 1;
        s = getParameter("num");
        num = s != null ? Integer.valueOf(s).intValue() : 1;
        s = getParameter("dy");
        dy = s != null ? Integer.valueOf(s).intValue() : 50;
        s = getParameter("dist");
        dist = s != null ? Float.valueOf(s).floatValue() : 1.0F;
        s = getParameter("speed");
        speed = s != null ? Float.valueOf(s).floatValue() : Pi2 / 400F;
        s = getParameter("bgcolor");
        bg = s != null ? new Color(Integer.valueOf(s, 16).intValue()) : getBackground();
        s = getParameter("fgcolor");
        fg = s != null ? new Color(Integer.valueOf(s, 16).intValue()) : bg;
        lc = fg.brighter();
        dc = fg.darker();
        buffer_i = createImage(size().width, size().height);
        buffer_g = buffer_i.getGraphics();

    }
    //启动小程序
    public void start()
    {
    	try
        {
            showStatus("dynarule: Loading image...");
            tracker.waitForID(0);
            showStatus("");

        }
        catch(InterruptedException _ex)
        {
            showStatus("dynarule: Image loading interrupted");
            return;

        }
        am = (xs - img.getWidth(this)) / 2;
        of = am;
        x = 0.0F;
        if(kicker == null)
        {
            kicker = new Thread(this);
            kicker.start();

        }

    }
    //停止小程序
    public void stop()
    {
        if(kicker != null)
        {
            kicker.stop();
            kicker = null;
        }
    }
    //运行小程序
    public void run()
    {
        do
        {
            x += speed;
            if(x > Pi2)
                x -= Pi2;
            repaint();
            try
            {
                Thread.sleep(15L);
            }
            catch(InterruptedException _ex) { }
        }
        while(true);
    }
    //画屏函数
    public void paint(Graphics g)
    {
        buffer_g.setColor(bg);
        buffer_g.fillRect(0, 0, xs, ys);
        buffer_g.setColor(lc);
        buffer_g.drawLine(xs - 1, dy + size, 0, dy + size);
        buffer_g.drawLine(xs - 1, dy + size, xs - 1, dy);
        buffer_g.setColor(dc);
        buffer_g.drawLine(0, dy, xs - 1, dy);
        buffer_g.drawLine(0, dy, 0, dy + size);
        float f = x;
        for(int i = 0; i < num; i++)
        {
            int j = (int)(Math.sin(f) * (double)am + (double)of);
            buffer_g.drawImage(img, j, 0, this);
            f += dist;
        }
        g.drawImage(buffer_i, 0, 0, this);

    }
    //刷新屏幕函数
    public final synchronized void update(Graphics g)
    {
        paint(g);
    }
    //构造函数
    public dynarule()
    {
        Pi2 = 6.283185F;
    }
}