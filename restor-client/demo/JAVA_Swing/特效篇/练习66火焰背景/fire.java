//fire.java
import java.applet.Applet;
import java.awt.*;

public class fire extends Applet
    implements Runnable
{
    //变量定义
    boolean first = false;
    int ROWS = 0;
    int COLS = 0;
    int HIDDEN = 0;
    int ROWS_SEED = 0;
    int ROWS_RESEED = 0;
    int MAX_SEED = 0;
    int PALETTE_SIZE = 0;
    int COOLING_LIMIT = 0;
    int COOLING_ROWS = 0;
    int COOLING_FACTOR = 0;
    Color palette[] = null;
    byte Buffer[] = null;
    byte Buffer2[] = null;
    String message = null;
    String textfont = null;
    int textsize = 0;
    int textX = 0;
    int textY = 0;
    Color textcolor = null;
    Image offScrImage = null;
    Graphics offScrGC = null;
    Dimension offScrSize = null;
    Thread kicker = null;

    //得到小程序信息
    public String getAppletInfo()
    {
        return "hello";
    }
    //得到参数
    public String[][] getParameterInfo()
    {
        String as[][] = {
            {
                "coolingrows", "int", "number of rows to cool"
            }, {
                "coolingfactor", "int", "cooling factor"
            }, {
                "coolinglimit", "int", "cooling threshold"
            }, {
                "soundtrack", "url", "background sound"
            }, {
                "text", "String", "message"
            }, {
                "textcolor", "String", "text color"
            }, {
                "textfont", "String", "text font"
            }, {
                "textsize", "int", "text size"
            }
        };
        return as;
    }
    //初始化小程序
    public void init()
    {
        COLS = size().width;
        ROWS = size().height + HIDDEN;
        String s = getParameter("coolinglimit");
        if(s != null && s.endsWith("%"))
            s = s.substring(0, s.length() - 1);
        COOLING_LIMIT = s != null ? (PALETTE_SIZE * Integer.valueOf(s).intValue()) / 100 : (int)((double)PALETTE_SIZE * 0.5D);
        s = getParameter("coolingrows");
        if(s != null && s.endsWith("%"))
            s = s.substring(0, s.length() - 1);
        COOLING_ROWS = s != null ? (ROWS * Integer.valueOf(s).intValue()) / 100 : (int)((double)ROWS * 0.80000000000000004D);
        s = getParameter("coolingfactor");
        COOLING_FACTOR = s != null ? Integer.valueOf(s).intValue() : 2;
        ROWS_RESEED = (int)((double)ROWS * 0.95999999999999996D);
        s = getParameter("text");
        message = s != null ? s : "";
        s = getParameter("textfont");
        textfont = s != null ? s : "TimesRoman";
        s = getParameter("textsize");
        textsize = s != null ? Integer.valueOf(s).intValue() : 18;
        s = getParameter("textcolor");
        textcolor = hexColor(s, Color.white);
        Buffer = new byte[COLS * ROWS];
        Buffer2 = new byte[COLS * ROWS];
        for(int j = 0; j < 16; j++)
            palette[j] = new Color(16 * j, 0, 0);

        for(int k = 0; k < 16; k++)
            palette[16 + k] = new Color(255, 16 * k, 0);

        for(int l = 0; l < 32; l++)
            palette[32 + l] = new Color(255, 255, 8 * l);

        Font font = new Font(textfont, 1, textsize);
        FontMetrics fontmetrics = getFontMetrics(font);
        int i1 = fontmetrics.getHeight();
        int j1 = fontmetrics.stringWidth(message);
        textX = (COLS - j1) / 2;
        textY = ROWS - HIDDEN - (ROWS - HIDDEN - i1) / 2 - fontmetrics.getDescent();
        setFont(font);
        for(int i = COLS * (ROWS - ROWS_SEED); i < ROWS * COLS; i++)
            Buffer[i] = (byte)(int)(Math.random() * (double)(PALETTE_SIZE - 1));

    }
    //主循环
    void MainLoop()
    {
        for(int i = COLS + 1; i < COLS * (ROWS - 1) - 1; i++)
        {
            int k = Buffer[i - COLS - 1] + Buffer[i - COLS] + Buffer[(i - COLS) + 1] + Buffer[i - 1] + Buffer[i + 1] + Buffer[(i + COLS) - 1] + Buffer[i + COLS] + Buffer[i + COLS + 1];
            k >>= 3;
            if(k < COOLING_LIMIT && i < COOLING_ROWS * COLS && k > COOLING_FACTOR)
                k -= COOLING_FACTOR;
            Buffer2[i] = (byte)k;
        }

        for(int j = COLS * ROWS_RESEED; j < COLS * ROWS; j++)
        {
            int l = Buffer2[j];
            Buffer2[j] = (byte)(int)(((double)l - Math.random() * (double)MAX_SEED) % ((double)PALETTE_SIZE * 1.1000000000000001D));
        }

        for(int i1 = 0; i1 < COLS * (ROWS - 1); i1++)
            Buffer[i1] = Buffer2[i1 + COLS];

    }
    //更新小程序
    public final synchronized void update(Graphics g)
    {
        Dimension dimension = size();
        if(offScrImage == null || dimension.width != offScrSize.width || dimension.height != offScrSize.height)
        {
            offScrImage = createImage(dimension.width, dimension.height);
            offScrSize = dimension;
            offScrGC = offScrImage.getGraphics();
            offScrGC.setFont(getFont());
        }
        if(offScrGC != null)
        {
            offScrGC.fillRect(0, 0, dimension.width, dimension.height);
            paint(offScrGC);
            g.drawImage(offScrImage, 0, 0, null);
        }
    }
    //画屏函数
    public void paint(Graphics g)
    {
        MainLoop();
        for(int j = 0; j < ROWS - HIDDEN; j++)
        {
            for(int k = 0; k < COLS; k++)
            {
                int i = Buffer[j * COLS + k];
                i = i >= 0 ? i : -i;
                i = i >= PALETTE_SIZE - 1 ? PALETTE_SIZE - 1 : i;
                Color color = palette[i];
                try
                {
                    offScrGC.setColor(color);
                    offScrGC.drawLine(k, j, k + 1, j);
                }
                catch(Exception _ex) { }
            }

        }

        try
        {
            offScrGC.setColor(textcolor);
            offScrGC.drawString(message, textX, textY);
            g.drawImage(offScrImage, 0, 0, this);
            return;
        }
        catch(Exception _ex)
        {
            return;
        }
    }
    //启动小程序
    public void start()
    {
        if(kicker == null)
        {
            kicker = new Thread(this);
            kicker.start();
        }
    }

    public void stop()
    {
        kicker = null;
    }
    //运行小程序
    public void run()
    {
        while(kicker != null) 
        {
            repaint();
            try
            {
                Thread.sleep(15L);
            }
            catch(InterruptedException _ex) { }
        }

    }
    //响应按下鼠标
    public boolean mouseDown(Event event, int i, int j)
    {
        int k = i + j * COLS;
        if(k > 81)
        {
            Buffer[k] = -1;
            Buffer[k - COLS] = -1;
            Buffer[k + COLS] = -1;
            Buffer[k - 1] = -1;
            Buffer[k + 1] = -1;
        }
        return true;
    }
    //处理颜色
    public Color hexColor(String s, Color color)
    {
        try
        {
            Integer integer = new Integer(0);
            s.replace('#', ' ');
            s.trim();
            integer = Integer.valueOf(s, 16);
            return new Color(integer.intValue());
        }
        catch(Exception _ex)
        {
            return color;
        }
    }
    //构造函数
    public fire()
    {
        first = true;
        ROWS = 50;
        COLS = 64;
        HIDDEN = 4;
        ROWS_SEED = 4;
        ROWS_RESEED = 48;
        MAX_SEED = 8;
        PALETTE_SIZE = 64;
        COOLING_LIMIT = 32;
        COOLING_ROWS = 42;
        COOLING_FACTOR = 2;
        palette = new Color[PALETTE_SIZE];
    }
}