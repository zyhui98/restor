//Shaking.java
import java.applet.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Shaking extends Applet
    implements Runnable
{
    //变量声明
    Thread runner = null;
    MediaTracker tracker = null;
    Image ThePicture = null;
    int amplitude = 0;
    int shakespeed = 0;
    boolean mouseIn = false;
    AudioClip sound = null;
    URL url = null;
    String target = null;
    String statusText = null;
    Color bgColor = null;
    Image offscreenI = null;
    Graphics offscreenG = null;
    boolean breaker = false;
    boolean netscape = false;
    //初始化小程序
    public void init()
    {
        tracker = new MediaTracker(this);
        String tmp = getParameter("picture");
        if(tmp != null)
            ThePicture = getImage(getCodeBase(), tmp);
        else
            error("Didn't find a picture to draw.");
        tracker.addImage(ThePicture, 0);
        try
        {
            tracker.waitForID(0);
        }
        catch(InterruptedException _ex)
        {
            return;
        }
        tmp = getParameter("amplitude");
        if(tmp == null)
        {
            amplitude = 2;
        }
        else
        {
            amplitude = Integer.parseInt(tmp);
            if(amplitude <= 0)
                amplitude = 1;
        }
        tmp = getParameter("shakespeed");
        if(tmp == null)
        {
            shakespeed = 10;
        }
        else
        {
            shakespeed = Integer.parseInt(tmp);
            if(shakespeed <= 0)
                shakespeed = 1;
        }
        tmp = getParameter("sound");
        sound = getAudioClip(getCodeBase(), tmp);
        tmp = getParameter("url");
        if(tmp != null)
        {
            if(!tmp.startsWith("http://") && !tmp.startsWith("mailto:"))
            {
                String codebase = getCodeBase().toString();
                int codebaseLength = codebase.length();
                String codebaseEnd = codebase.substring(codebaseLength - 4, codebaseLength);
                codebaseEnd.toUpperCase();
                boolean _tmp = codebaseEnd.equals("HTML") || codebase.equals(".HTM");
                int lastSlash = codebase.lastIndexOf("/");
                codebase = codebase.substring(0, lastSlash + 1);
                tmp = codebase + tmp;
            }
            try
            {
                url = new URL(tmp);
            }
            catch(MalformedURLException _ex)
            {
                error("Illegal url specified");
            }
            target = getParameter("target");
        }
        statusText = getParameter("statustext");
        tmp = getParameter("bgcolor");
        if(tmp != null)
        {
            if(tmp.startsWith("#"))
            {
                tmp.toUpperCase();
                int bgcolor = 0;
                for(int place = tmp.length(); place > 1; place--)
                {
                    char c = tmp.charAt(place - 1);
                    if(Character.digit(c, 16) == -1)
                        error("Error bgcolor parameter isn't hexadecimal");
                    else
                        bgcolor = (int)((double)bgcolor + (double)Character.digit(c, 16) * Math.pow(16D, tmp.length() - place));
                }

                if(bgcolor <= 0xffffff)
                    bgColor = new Color(bgcolor);
                else
                    bgColor = Color.black;
            }
            else
            {
                bgColor = new Color(Integer.parseInt(tmp));
            }
        }
        else
        {
            bgColor = Color.black;
        }
        offscreenI = createImage(size().width, size().height);
        offscreenG = offscreenI.getGraphics();
        offscreenG.setColor(bgColor);
        offscreenG.fillRect(0, 0, size().width, size().height);
        String vendor = System.getProperty("java.vendor").toUpperCase();
        netscape = vendor.startsWith("NETSCAPE");
    }
    //界面消息响应
    public boolean handleEvent(Event evt)
    {
        switch(evt.id)
        {
        case 504: // Event.MOUSE_EVENT
            mouseIn = true;
            if(statusText == null)
            {
                if(url != null)
                    getAppletContext().showStatus(url.toString());
            }
            else
            if(statusText != null)
                getAppletContext().showStatus(statusText);
            if(sound != null)
                sound.play();
            return true;

        case 505: // Event.MOUSE_EXIT
            mouseIn = false;
            repaint();
            getAppletContext().showStatus(" ");
            return true;

        case 501: // Event.MOUSE_DOWN
            if(mouseIn)
                if(target == null)
                    getAppletContext().showDocument(url);
                else
                    getAppletContext().showDocument(url, target);
            return true;

        }
        return false;
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
    //清空对象的内存空间
    public void destroy()
    {
        runner = null;
        ThePicture = null;
        System.gc();
        super.destroy();
    }
    //运行小程序
    public void run()
    {
        while(Thread.currentThread() == runner) 
        {
            int horAmpli = (int)((double)amplitude * Math.random() + 0.5D);
            int verAmpli = (int)((double)amplitude * Math.random() + 0.5D);
            if(netscape && verAmpli == 0 && horAmpli != 0)
                verAmpli = 1;
            offscreenG.drawImage(ThePicture, horAmpli, verAmpli, size().width - 2 * horAmpli, size().height - 2 * verAmpli, this);
            breaker = true;
            repaint();
            pause(shakespeed);
            breaker = false;
        }

    }
    //更新屏幕
    public void update(Graphics g)
    {
        if(breaker)
            g.drawImage(offscreenI, 0, 0, this);
    }
    //画屏函数
    public void paint(Graphics g)
    {
        update(g);
    }
    //暂停小程序
    public void pause(int time)
    {
        try
        {
            Thread.sleep(time);
            return;
        }
        catch(InterruptedException _ex)
        {
            return;
        }
    }
    //出错处理
    public void error(String error)
    {
        getAppletContext().showStatus(error);
        pause(5000);
    }

    //构造函数
    public Shaking()
    {
        breaker = false;
    }
}