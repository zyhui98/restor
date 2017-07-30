//flame.java
import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.StringTokenizer;

public class flame extends Applet
    implements Runnable
{
    //变量声明
    static final int XSIZE = 300;
    static final int YSIZE = 100;
    static final int XS = 1;
    static final int XE = 299;
    static final int YS = 1;
    static final int XB = 15;
    static final int BOTTOM = 29700;
    static final int MAXPAL = 256;
    int pal[] = null;
    static final int SMOOTH = 1;
    static final int decay = 6;
    Random rd = null;
    Image art = null;
    Thread motor = null;
    int off[] = null;
    int count = 0;
    int burn = 0;
    static final int YLEN = 50;
    static final long TW_DLAY = 8000L;
    static final long TW_FADE = 5000L;
    static final int TW_LEN = 1000;
    static final int TW_MAXCOLOR = 30;
    int tw_words[][] = null;
    int tw_max = 0;
    int tw_count = 0;
    long tw_timer = 0;
    int tw_color[] = null;
    boolean tw_isLink = false;
    String tw_link = null;
    static final int gXSIZE = 300;
    static final int gYSIZE = 10;

    //响应鼠标事件
    public boolean handleEvent(Event event)
    {
        if(event.target == this)
            switch(event.id)
            {
            case 501: // Event.MOUSE_DOWN
                burn = 40;
                return true;

            case 503: // Event.MOUSE_MOVE
                if(!tw_isLink || event.y < 100)
                    showStatus("Click to cool flames.");
                else
                    showStatus("Link to " + tw_link);
                return true;

            case 502: // Event.MOUSE_UP
                if(!tw_isLink || event.y < 100)
                    return true;
                URL url;
                try
                {
                    url = new URL(tw_link);
                }
                catch(MalformedURLException _ex)
                {
                    return false;
                }
                stop();
                getAppletContext().showDocument(url);
                return true;

            }
        return super.handleEvent(event);
    }
    //产生随机数
    int rand(int n)
    {
        return Math.abs(rd.nextInt() % n);
    }
    //得到颜色值
    int colorit(int c)
    {
        return pal[c & 0xff];
    }
    //变换颜色
    int coloroff(int c)
    {
        return (c & 0xff0000) >> 16;
    }
    //行扫描
    void processline(int i)
    {
        int offset = 300;
        int poffset = offset - 300;
        if(i <= 1 || i >= 299)
        {
            for(int j = 1; j < 100; j++)
            {
                off[i + poffset] = 0xff000000;
                poffset += 300;
            }

            return;
        }
        for(int j = 1; j < 100; j++)
        {
            int c = coloroff(off[i + offset]);
            if(c < 6)
                off[i + poffset] = 0xff000000;
            else
                off[(i - (rand(3) - 1)) + poffset] = colorit(c - rand(6));
            poffset += 300;
            offset += 300;
        }

    }
    //颜色处理
    void smoothBottom()
    {
        for(int i = 2; i < 298; i++)
        {
            int total = 0;
            for(int j = -1; j <= 1; j++)
                total += coloroff(off[29700 + i + j]);

            off[29700 + i] = colorit(total / 3);
        }

    }
    //随机染色
    void splashRandom()
    {
        int d = rand(15);
        int max = 298;
        for(int i = 0; i < d; i++)
        {
            int c = rand(max);
            int col = coloroff(off[29701 + c]);
            if((col &= 0x7) < 0)
                col = 0;
            off[29701 + c] = colorit(col);
        }

    }
    //染色
    void splash()
    {
        int max = 298;
        for(int i = 0; i < 25; i++)
        {
            int d = rand(4) + 2;
            int c = rand(max - d);
            for(int j = 0; j < d; j++)
            {
                off[29701 + c] = 0xff000000;
                c++;
            }

        }

    }
    //染色过程
    public synchronized void process()
    {
        int mid = 150;
        for(int i = rand(8); i > 0; i--)
        {
            int c = rand(268) + 15;
            for(int j = 0; j < 10; j++)
                off[29700 + c + j] = colorit(180 + rand(76));

        }

        if(burn > 0)
        {
            splash();
            burn--;
        }
        splashRandom();
        smoothBottom();
        for(int i = 1; i < mid; i++)
            processline(i);

        for(int i = 299; i >= mid; i--)
            processline(i);

    }
    //调色
    void makepal()
    {
        for(int i = 0; i < 256; i++)
            pal[i] = 0xff000000;

        for(int i = 1; i < 110; i++)
            pal[i] = 0xff000000 | i << 16 | (i * 3) / 5 << 8 | i / 5;

        for(int i = 110; i < 256; i++)
        {
            int c = pal[i - 1] & 0xff00ffff;
            if((c & 0xff00) < 60928)
                c += 256;
            if((c & 0xff00) < 60928)
                c += 256;
            if((c & 0xff) < 255)
                c++;
            pal[i] = 0xff000000 | c | i << 16;
        }

    }
    //设置显示效果
    void tw_begin()
    {
        for(int i = 0; i < 30; i++)
            tw_color[i] = pal[170 + i] + 80;

        tw_isLink = true;
        tw_link = getParameter("link");
        if(tw_link == null)
            tw_isLink = false;
        String fts = getParameter("font");
        if(fts == null)
            fts = "TimesRoman";
        Image twImg = createImage(300, 50);
        Graphics twGrp = twImg.getGraphics();
        twGrp.setFont(new Font(fts, 1, 40));
        FontMetrics fm = twGrp.getFontMetrics();
        String words = getParameter("text");
        if(words == null)
            words = "IonChron+presents+Flames+by+IoN CheN";
        StringTokenizer st = new StringTokenizer(words, "+");
        tw_max = st.countTokens();
        tw_words = new int[tw_max][1000];
        tw_count = 0;
        while(st.hasMoreTokens()) 
        {
            clearOff();
            String str = st.nextToken();
            twGrp.setColor(Color.black);
            twGrp.fillRect(0, 0, 300, 50);
            twGrp.setColor(Color.white);
            twGrp.drawString(str, (300 - fm.stringWidth(str)) / 2, 40);
            PixelGrabber pg = new PixelGrabber(twImg, 0, 0, 300, 50, off, 0, 300);
            boolean grabbed = true;
            try
            {
                pg.grabPixels();
            }
            catch(InterruptedException _ex)
            {
                grabbed = false;
            }
            if(grabbed)
            {
                int i = 1;
                for(int keep = 0; keep < 15000 && i < 999;)
                {
                    int cnt;
                    for(cnt = 0; keep < 15000 && (off[keep++] & 0xffffff) == 0; cnt++);
                    tw_words[tw_count][i++] = cnt + 1;
                    for(cnt = 1; keep < 15000 && (off[keep++] & 0xffffff) != 0; cnt++);
                    tw_words[tw_count][i++] = cnt;
                }

                tw_words[tw_count][0] = i - 1;
                tw_count++;
            }
        }

        tw_max = tw_count;
        twGrp = null;
        twImg.flush();
        twImg = null;
        tw_count = tw_max - 1;
        tw_timer = (System.currentTimeMillis() + 8000L) - 5000L;
    }
    //显示过程
    void tw_process()
    {
        long ctime = System.currentTimeMillis();
        if(ctime <= tw_timer)
        {
            if(ctime < tw_timer - 5000L)
            {
                int i = 1;
                int offset = 9000;
                for(int max = tw_words[tw_count][0]; i < max;)
                {
                    offset += tw_words[tw_count][i++];
                    for(int count = tw_words[tw_count][i++]; --count >= 0;)
                        off[offset++] = tw_color[rand(30)];

                }

                return;
            }
        }
        else
        {
            if(++tw_count >= tw_max)
                tw_count = 0;
            tw_timer = System.currentTimeMillis() + 8000L;
        }
    }
    //画屏函数
    public void paint(Graphics g)
    {
        g.setFont(new Font("Helvetica", 0, 9));
        g.setColor(new Color(255, 200, 240));
        g.drawString("Flames  (C)  CopyRight  1997  I-Yuan IoN Chen", 90, 109);
        update(g);
    }
    //更新屏幕函数
    public void update(Graphics g)
    {
        art = createImage(new MemoryImageSource(300, 100, off, 0, 300));
        g.drawImage(art, 0, 0, null);
        art.flush();
    }
    //初始化小程序
    public void init()
    {
        setLayout(null);
        addNotify();
        resize(300, 110);
        setBackground(new Color(0, 20, 40));
    }
    //开始小程序
    public void start()
    {
        makepal();
        motor = new Thread(this);
        motor.setPriority(1);
        motor.start();
    }
    //停止小程序
    public void stop()
    {
        motor.stop();
    }

    void clearOff()
    {
        for(int i = 0; i < 30000; i++)
            off[i] = 0xff000000;

    }

    void drawBottom()
    {
    }
    //运行小程序
    public void run()
    {
        tw_begin();
        clearOff();
        do
        {
            process();
            tw_process();
            repaint();
            try
            {
                Thread.sleep(1L);
            }
            catch(InterruptedException _ex) { }
        }
        while(true);
    }
    //构造函数
    public flame()
    {
        pal = new int[256];
        rd = new Random();
        off = new int[30000];
        tw_color = new int[30];
    }
}
