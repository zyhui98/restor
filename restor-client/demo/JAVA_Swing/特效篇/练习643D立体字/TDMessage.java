//TDMessage.java
import java.applet.Applet;
import java.awt.*;
import java.util.StringTokenizer;

public class TDMessage extends Applet
    implements Runnable
{
    //变量定义
    Thread m_TDMessage = null;
    Image off = null;
    Graphics g_off = null;
    Font font = null;
    FontMetrics fm = null;
    Dimension d = null;
    int font_style = 0;
    int center_x = 0;
    boolean gSok = false;
    boolean on_the_move = false;
    private int m_speed = 0;
    private int m_size = 0;
    private int m_3d_size = 0;
    private int m_delay = 0;
    private String m_messagefont = null;
    private boolean m_bold = false;
    private boolean m_italic = false;
    private int m_movedelay = 0;
    private int m_num_msg = 0;
    private String m_msg[] = null;
    private int m_heading[] = null;
    Color m_bgcolor = null;
    //启动小程序
    public void start()
    {
        if(m_TDMessage == null)
        {
            m_TDMessage = new Thread(this);
            m_TDMessage.start();
        }
    }
    //停止小程序
    public void stop()
    {
        if(m_TDMessage != null)
        {
            m_TDMessage.stop();
            m_TDMessage = null;
        }
    }
    //构造函数
    public TDMessage()
    {
        m_speed = 40;
        m_size = 24;
        m_3d_size = 10;
        m_delay = 3000;
        m_messagefont = "arial";
        m_movedelay = 10;
        m_bgcolor = Color.white;
    }
    //制作3d文字
    public void draw3d(Graphics g)
    {
        int i = 0;
        int y = 0;
        int move = 0;
        if(!gSok)
        {
            y = (d.height - m_size) / 2 + m_size;
            g.drawString("Invalid 'Notice' Param", 0, y);
            g_off.drawString("Invalid 'Notice' Param", 0, y);
            return;
        }
        y = d.height / 2 + (fm.getAscent() + fm.getLeading()) / 2;
        for(int k = 0; k < m_num_msg; k++)
        {
            center_x = d.width / 2 - fm.stringWidth(m_msg[k]) / 2 - 255 / m_3d_size;
            i = 0;
            do
            {
                move++;
                g.drawString(m_msg[k], center_x + move, y);
                g.setColor(new Color(i, i, i));
                g_off.drawString(m_msg[k], center_x + move, y);
                g_off.setColor(new Color(i, i, i));
                try
                {
                    Thread.sleep(m_speed);
                }
                catch(InterruptedException e) { }
                i += m_3d_size;
            }
            while(i < 255);
            try
            {
                Thread.sleep(1000L);
            }
            catch(InterruptedException e) { }
            i = 255;
            do
            {
                g.drawString(m_msg[k], center_x + move, y);
                g.setColor(new Color(i, i, i));
                g_off.drawString(m_msg[k], center_x + move, y);
                g_off.setColor(new Color(i, i, i));
                try
                {
                    Thread.sleep(m_speed);
                }
                catch(InterruptedException e) { }
                i -= m_3d_size;
            }
            while(i > 0);
            move = 0;
            try
            {
                Thread.sleep(m_delay);
            }
            catch(InterruptedException e) { }
            on_the_move = true;
            if(m_heading[k] == 2)
            {
                for(int j = 0; j <= d.height; j++)
                {
                    g.drawImage(off, 0, j, this);
                    try
                    {
                        Thread.sleep(m_movedelay);
                    }
                    catch(InterruptedException e) { }
                }

            }
            if(m_heading[k] == 0)
            {
                for(int j = 0; j >= -d.height; j--)
                {
                    g.drawImage(off, 0, j, this);
                    try
                    {
                        Thread.sleep(m_movedelay);
                    }
                    catch(InterruptedException e) { }
                }

            }
            if(m_heading[k] == 1)
            {
                for(int j = 0; j <= d.width; j++)
                {
                    g.drawImage(off, j, 0, this);
                    try
                    {
                        Thread.sleep(m_movedelay);
                    }
                    catch(InterruptedException e) { }
                }

            }
            if(m_heading[k] == 3)
            {
                for(int j = 0; j >= -d.width; j--)
                {
                    g.drawImage(off, j, 0, this);
                    try
                    {
                        Thread.sleep(m_movedelay);
                    }
                    catch(InterruptedException interruptedexception) { }
                }

            }
            DrawBG(g);
            on_the_move = false;
        }

    }
    //填充颜色
    public void DrawBG(Graphics g)
    {
        g.setColor(m_bgcolor);
        g.fillRect(0, 0, d.width, d.height);
        g_off.setColor(m_bgcolor);
        g_off.fillRect(0, 0, d.width, d.height);
    }
    //运行小程序
    public void run()
    {
        do
            try
            {
                draw3d(getGraphics());
                Thread.sleep(50L);
            }
            catch(InterruptedException e)
            {
                stop();
            }
        while(true);
    }
    //转换颜色
    public Color ConvertColor(String temp)
    {
        int r = 0;
        int g = 0;
        int b = 0;
        temp.trim();
        StringTokenizer st = new StringTokenizer(temp, ",");
        if(st.countTokens() > 3)
            return Color.black;
        while(st.hasMoreTokens()) 
        {
            r = Integer.parseInt(st.nextToken().trim());
            g = Integer.parseInt(st.nextToken().trim());
            b = Integer.parseInt(st.nextToken().trim());
        }

        st = null;
        if(r > 255)
            r = 255;
        if(g > 255)
            g = 255;
        if(b > 255)
            b = 255;
        Color ctemp = new Color(r, g, b);
        return ctemp;
    }
    //初始化小程序
    public void init()
    {
        String crstr = "3D Message Effect";
        d = size();
        String param = getParameter("Notice");
        if(param != null)
            if(param.equals(crstr))
                gSok = true;
            else
                gSok = false;
        param = getParameter("delay3d");
        if(param != null)
            m_speed = Integer.parseInt(param);
        param = getParameter("numofmessages");
        if(param != null)
            m_num_msg = Integer.parseInt(param);
        param = getParameter("movedelay");
        if(param != null)
            m_movedelay = Integer.parseInt(param);
        param = getParameter("size");
        if(param != null)
            m_size = Integer.parseInt(param);
        param = getParameter("font");
        if(param != null)
            m_messagefont = param;
        param = getParameter("3dsize");
        if(param != null)
            m_3d_size = Integer.parseInt(param);
        param = getParameter("delay");
        if(param != null)
            m_delay = Integer.parseInt(param);
        param = getParameter("bgcolor");
        if(param != null)
        {
            m_bgcolor = ConvertColor(param);
            setBackground(m_bgcolor);
        }
        else
        {
            m_bgcolor = new Color(0);
            setBackground(m_bgcolor);
        }
        param = getParameter("italic");
        if(param != null)
            m_italic = Boolean.valueOf(param).booleanValue();
        param = getParameter("bold");
        if(param != null)
            m_bold = Boolean.valueOf(param).booleanValue();
        if(m_bold)
            font_style = font_style + 1;
        if(m_italic)
            font_style = font_style + 2;
        setFont(font = new Font(m_messagefont, font_style, m_size));
        fm = getFontMetrics(font);
        m_msg = new String[m_num_msg];
        m_heading = new int[m_num_msg];
        for(int i = 0; i < m_num_msg; i++)
        {
            param = getParameter("desc" + i);
            if(param != null)
                m_msg[i] = new String(param);
            else
                m_msg[i] = new String(" ");
            param = getParameter("heading" + i);
            if(param != null)
            {
                m_heading[i] = Integer.parseInt(param);
                if(m_heading[i] > 3 && m_heading[i] < 0)
                    m_heading[i] = 4;
            }
            else
            {
                m_heading[i] = (int)(Math.random() * 4D);
            }
        }

        off = createImage(d.width, d.height);
        g_off = off.getGraphics();
        g_off.setFont(font = new Font(m_messagefont, font_style, m_size));
    }
    //画屏函数
    public void paint(Graphics g)
    {
        if(!on_the_move)
            g.drawImage(off, 0, 0, this);
    }
}
