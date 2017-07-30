//dynarule.java
import java.applet.Applet;
import java.awt.*;

public class dynarule extends Applet
    implements Runnable
{
    //变量定义
    int xs;
    int ys;
    int size;
    int dy;
    int num;
    Image img;
    Color fg;
    Color bg;
    Color lc;
    Color dc;
    float am;
    float of;
    float x;
    float dist;
    float speed;
    MediaTracker tracker;
    Image buffer_i;
    Graphics buffer_g;
    Thread kicker;
    float Pi2;
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
        size = s == null ? 1 : Integer.valueOf(s).intValue();
        s = getParameter("num");
        num = s == null ? 1 : Integer.valueOf(s).intValue();
        s = getParameter("dy");
        dy = s == null ? 50 : Integer.valueOf(s).intValue();
        s = getParameter("dist");
        dist = s == null ? 1.0F : Float.valueOf(s).floatValue();
        s = getParameter("speed");
        speed = s == null ? Pi2 / 400F : Float.valueOf(s).floatValue();
        s = getParameter("bgcolor");
        bg = s == null ? getBackground() : new Color(Integer.valueOf(s, 16).intValue());
        s = getParameter("fgcolor");
        fg = s == null ? bg : new Color(Integer.valueOf(s, 16).intValue());
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
        catch(InterruptedException interruptedexception)
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
            catch(InterruptedException interruptedexception) { }
        } while(true);
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
    //更新屏幕函数
    public final synchronized void update(Graphics g)
    {
        paint(g);
    }
    //构造函数
    public dynarule()
    {
        xs = 0;
        ys = 0;
        size = 0;
        dy = 0;
        num = 0;
        img = null;
        fg = null;
        bg = null;
        lc = null;
        dc = null;
        am = 0.0F;
        of = 0.0F;
        x = 0.0F;
        dist = 0.0F;
        speed = 0.0F;
        tracker = null;
        buffer_i = null;
        buffer_g = null;
        kicker = null;
        Pi2 = 0.0F;
        Pi2 = 6.283185F;
    }
}