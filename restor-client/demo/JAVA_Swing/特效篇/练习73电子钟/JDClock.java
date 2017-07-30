//JDClock.java
import java.applet.Applet;
import java.awt.*;
import java.util.Date;

public class JDClock extends Applet
    implements Runnable
{
    //变量定义
    MediaTracker tracker ;
    Thread timer ;
    boolean suspended = false;
    Polygon SegmentPolygon[] ;
    int NumberSegment[][] = {
    	
        {0, 1, 2, 3, 4, 5, -1, -1},
        {1, 2, -1, -1, -1, -1, -1, -1},
        {0, 1, 3, 4, 6, -1, -1, -1},
        {0, 1, 2, 3, 6, -1, -1, -1},
        {1, 2, 5, 6, -1, -1, -1, -1},
        {0, 2, 3, 5, 6, -1, -1, -1},
        {0, 2, 3, 4, 5, 6, -1, -1},
        {0, 1, 2, -1, -1, -1, -1, -1},
        {0, 1, 2, 3, 4, 5, 6, -1},
        {0, 1, 2, 3, 5, 6, -1, -1},
        {7, 8, -1, -1, -1, -1, -1, -1},
        {9, -1, -1, -1, -1, -1, -1, -1}
    
    };

    int WeekPointX[][] = {
    	
        {
            
            0, 0, 1, 1, 1, 5, 1, 3, 5, 5,
            6, 6, 4, 4, 5, 2, 2, 2, 3, 3,
            3, 6, 6, 7, 9, 9, 8, 8, 7, 8,
            8, 11, 10, 11, 11, 12, 12, 12, 12, 13,
            -1
        
        }, {
        
            0, 0, 1, 1, 2, 2, 3, 3, 4, 4,
            5, 5, 6, 6, 6, 6, 7, 7, 7, 7,
            8, 8, 9, 9, 9, 9, 10, 10, 10, 12,
            12, 13, 13, 14, 14, 14, 14, 11, 11, 11,
            -1
        
        }, {
         
            0, 5, 2, 2, 1, 1, 0, 0, 6, 6,
            5, 5, 4, 5, 5, 8, 7, 8, 8, 9,
            9, 9, 9, 10, -1
        
        }, {
        
            0, 0, 1, 1, 2, 2, 3, 3, 4, 4,
            5, 5, 5, 4, 6, 6, 7, 7, 8, 8,
            9, 9, 11, 9, 11, 13, 13, 13, 13, 9,
            9, 9, 9, 11, 11, 10, 11, 12, -1
        
        }, {
        
            0, 5, 2, 2, 1, 1, 0, 0, 4, 5,
            5, 5, 6, 6, 7, 7, 7, 10, 10, 10,
            9, 9, 8, 8, -1
        
        }, {
        
            6, 2, 2, 2, 5, 1, 1, 1, 0, 0,
            10, 8, 8, 8, 7, 7, 6, 6, -1
        
        }, {
        
            6, 7, 7, 3, 2, 2, 3, 3, 4, 4,
            5, 5, 5, 0, 0, 1, 1, 3, 6, 6,
            7, 7, 8, 8, 7, 12, 10, 11, 11, 11,
            12, 12, 12, 9, 9, 8, 9, 12, -1
        
        }
    
    };
    int WeekPointY[][] = {
      
        {
      
            5, 6, 5, 7, 6, 6, 7, 7, 6, 3,
            5, 4, 4, 3, 3, 3, 3, 1, 3, 0,
            0, 0, 1, 1, 2, 3, 3, 7, 6, 6,
            7, 7, 6, 6, 5, 5, 5, 2, 2, 2,
            -1
       
        }, {
       
            7, 6, 6, 3, 4, 0, 0, 7, 7, 0,
            4, 5, 2, 3, 6, 7, 7, 4, 2, 0,
            0, 4, 0, 1, 4, 6, 3, 7, 7, 7,
            6, 6, 5, 5, 5, 2, 2, 2, 2, 3,
            -1
       
        }, {
        
            0, 0, 0, 3, 2, 6, 5, 7, 2, 3,
            3, 7, 6, 6, 7, 7, 6, 6, 5, 5,
            5, 2, 2, 2, -1
       
        }, {
        
            0, 7, 0, 6, 3, 5, 2, 3, 0, 2,
            0, 7, 7, 7, 4, 6, 3, 4, 1, 3,
            0, 1, 2, 4, 2, 2, 2, 4, 4, 4,
            4, 7, 7, 7, 7, 6, 7, 6, -1
       
        }, {
        
            0, 0, 0, 3, 3, 6, 5, 7, 7, 7,
            7, 4, 5, 1, 3, 0, 2, 2, 2, 4,
            2, 7, 7, 6, -1
       
        }, {
         
            0, 0, 0, 3, 3, 3, 2, 6, 5, 7,
            2, 2, 2, 4, 3, 7, 6, 7, -1
        
        }, {
          
            1, 1, 0, 0, 1, 3, 2, 3, 3, 4,
            3, 6, 6, 6, 5, 5, 7, 7, 4, 5,
            5, 7, 5, 7, 7, 7, 6, 6, 7, 2,
            2, 4, 4, 4, 4, 3, 2, 2, -1
        
        }
    
    };
    int WeekOffset[] = {4, 20, 40, 55, 74, 88, 101};
    Image image[] ;
    Image wimage[] ;
    Image buf_image ;
    Graphics gc ;
    int bdigit_width = 0;
    int bdigit_height = 0;
    int base_width = 0;
    int base_height = 0;
    int offset = 0;
    long TimeDiff = 0;
    boolean ShowWeek = false;
    boolean ShowDate = false;
    boolean Show24Hour = false;
    boolean ShowFrame = false;
    Color BorderLight ;
    Color BorderMideum ;
    Color BorderDark ;
    Color BGColor ;
    Color FontColor ;
    Color DayColor ;
    Color TodayColor ;
    Color SundayColor ;
    //构造函数
    public JDClock()
    {
    
        SegmentPolygon = new Polygon[10];
        image = new Image[12];
        wimage = new Image[7];
        bdigit_width = 15;
        bdigit_height = 20;
        base_width = 130;
        base_height = 65;
        offset = 5;
        ShowWeek = true;
        ShowDate = true;
        Show24Hour = true;
        ShowFrame = true;
    
    }
    //得到字符串和整数类型的参数
    int getParameter(String s1, int s2) 
    {
  
        String s = getParameter(s1) ;
        return (s != null) ? Integer.parseInt(s) : s2 ;
   
    }
    //得到字符串型的参数
    String getParameter(String s1, String s2) 
    {
  
        String s = getParameter(s1) ;
        return (s != null) ? s : s2 ;
  
    }
    //得到字符串和颜色类型的参数
    Color getParameter(String s1, Color s2)
    {
 
        String s = getParameter(s1);
        return (s != null) ? new Color(Integer.parseInt(s, 16)) : s2 ;
  
    }
    //得到字符串和字体类型的参数
    Font getParameter(String fn, String fs, String sz, Font s2)
    {
 
        int fontStyle = Font.PLAIN ;
        fs = getParameter(fs, "PLAIN") ;
        if (fs.toUpperCase().indexOf("BOLD") != -1) 
        	fontStyle |= Font.BOLD ;
        if (fs.toUpperCase().indexOf("ITALIC") != -1) 
        	fontStyle |= Font.ITALIC ;
        return ((fn = getParameter(fn)) != null) ?
          new Font(fn, fontStyle, getParameter(sz, s2.getSize())) :
          new Font(s2.getName(), fontStyle, 
          	getParameter(sz, s2.getSize())) ;
  
    }
    //初始化小程序
    public void init()
    {
        
        SegmentPolygon[0] = new Polygon();
        SegmentPolygon[0].addPoint(3, 2);
        SegmentPolygon[0].addPoint(13, 2);
        SegmentPolygon[0].addPoint(10, 5);
        SegmentPolygon[0].addPoint(6, 5);
        SegmentPolygon[1] = new Polygon();
        SegmentPolygon[1].addPoint(14, 2);
        SegmentPolygon[1].addPoint(14, 10);
        SegmentPolygon[1].addPoint(13, 10);
        SegmentPolygon[1].addPoint(11, 8);
        SegmentPolygon[1].addPoint(11, 5);
        SegmentPolygon[2] = new Polygon();
        SegmentPolygon[2].addPoint(14, 11);
        SegmentPolygon[2].addPoint(14, 18);
        SegmentPolygon[2].addPoint(11, 15);
        SegmentPolygon[2].addPoint(11, 12);
        SegmentPolygon[2].addPoint(12, 11);
        SegmentPolygon[3] = new Polygon();
        SegmentPolygon[3].addPoint(14, 19);
        SegmentPolygon[3].addPoint(2, 19);
        SegmentPolygon[3].addPoint(5, 16);
        SegmentPolygon[3].addPoint(11, 16);
        SegmentPolygon[4] = new Polygon();
        SegmentPolygon[4].addPoint(2, 18);
        SegmentPolygon[4].addPoint(2, 11);
        SegmentPolygon[4].addPoint(4, 11);
        SegmentPolygon[4].addPoint(5, 12);
        SegmentPolygon[4].addPoint(5, 15);
        SegmentPolygon[5] = new Polygon();
        SegmentPolygon[5].addPoint(2, 10);
        SegmentPolygon[5].addPoint(2, 2);
        SegmentPolygon[5].addPoint(5, 5);
        SegmentPolygon[5].addPoint(5, 8);
        SegmentPolygon[5].addPoint(3, 10);
        SegmentPolygon[6] = new Polygon();
        SegmentPolygon[6].addPoint(4, 10);
        SegmentPolygon[6].addPoint(5, 9);
        SegmentPolygon[6].addPoint(11, 9);
        SegmentPolygon[6].addPoint(12, 10);
        SegmentPolygon[6].addPoint(10, 12);
        SegmentPolygon[6].addPoint(5, 12);
        SegmentPolygon[7] = new Polygon();
        SegmentPolygon[7].addPoint(6, 4);
        SegmentPolygon[7].addPoint(9, 4);
        SegmentPolygon[7].addPoint(9, 7);
        SegmentPolygon[7].addPoint(6, 7);
        SegmentPolygon[8] = new Polygon();
        SegmentPolygon[8].addPoint(6, 13);
        SegmentPolygon[8].addPoint(9, 13);
        SegmentPolygon[8].addPoint(9, 16);
        SegmentPolygon[8].addPoint(6, 16);
        SegmentPolygon[9] = new Polygon();
        SegmentPolygon[9].addPoint(3, 8);
        SegmentPolygon[9].addPoint(12, 8);
        SegmentPolygon[9].addPoint(12, 11);
        SegmentPolygon[9].addPoint(3, 11);
        String s = getParameter("24HourTime");
        if(s != null && s.equals("no")) Show24Hour = false;
        s = getParameter("ShowWeek");
        if(s != null && s.equals("no"))
        {

            ShowWeek = false;
            base_height -= 10;

        }
        s = getParameter("ShowDate");
        if(s != null && s.equals("no"))
        {

            ShowDate = false;
            base_height -= 25;

        }
        s = getParameter("ShowFrame");
        if(s != null && s.equals("no"))
        {

            ShowFrame = false;
            offset = 0;
            base_width -= 10;
            base_height -= 10;

        }
        resize(base_width, base_height);
        BorderLight = getParameter("BorderColor", new Color(255)) ;
        BorderDark = new Color(BorderLight.getRed() / 3, 
           BorderLight.getGreen() / 3, BorderLight.getBlue() / 3);
        BorderMideum = new Color(BorderDark.getRed() * 2, 
           BorderDark.getGreen() * 2, BorderDark.getBlue() * 2);
        BGColor = getParameter("BGColor", Color.black);
        FontColor = getParameter("FontColor", new Color(65280));
        DayColor = getParameter("DayColor", new Color(0x808080));
        TodayColor = getParameter("TodayColor", new Color(65280));
        SundayColor = getParameter("SundayColor", 
        		new Color(0xff0000));
        tracker = new MediaTracker(this);
        int i = 0;
        do
        {

            image[i] = createImage(15, 20);
            Graphics g = image[i].getGraphics();
            g.setColor(BGColor);
            g.fillRect(0, 0, 15, 20);
            drawNumber(g, i);
            tracker.addImage(image[i], 0);

        }
        while(++i <= 11);
        if(ShowWeek)
        {

            int j = 0;
            do
            {

                wimage[j] = createImage(120, 10);
                Graphics g1 = wimage[j].getGraphics();
                g1.setColor(BGColor);
                g1.fillRect(0, 0, 120, 10);
                drawWeek(g1, j);
                tracker.addImage(wimage[j], 0);

            }
            while(++j < 7);

        }
        TimeDiff = 0L;
        s = getParameter("TimeZone");
        if(s != null && !s.equalsIgnoreCase("LOCAL"))
        {

            Date date = new Date();
            if(s.charAt(0) == '+')
                s = s.substring(1);
            try
            {

                TimeDiff = (long)Integer.parseInt(s) * 0x36ee80L + 
                	(long)date.getTimezoneOffset() * 60000L;
            
            }
            catch(Exception _ex) { }
        
        }
        try
        {

            buf_image = createImage(base_width, base_height);
            gc = buf_image.getGraphics();
            drawFrame(gc);
            return;

        }
        catch(Exception _ex)
        {

            gc = null;
   
        }

    }
    //响应鼠标动作
    public boolean handleEvent(Event event)
    {

        switch(event.id)
        {

        case Event.MOUSE_UP :
        default:
            break;

        case Event.MOUSE_DOWN :
            if(suspended) timer.resume();
            else timer.suspend();
            suspended = !suspended;
            break;

        case Event.MOUSE_MOVE :
            if(tracker.statusID(0, true) != 8) 
            	showStatus("initialing...please wait!");
            break;

        }
        return true;
 
    }
    //画数字
    void drawNumber(Graphics g, int i)
    {

        int j = 0;
        int k = 0;
        g.setColor(FontColor);
        while((k = NumberSegment[i][j++]) != -1) 
        	g.fillPolygon(SegmentPolygon[k]);

    }
    //设置星期几
    void drawWeek(Graphics g, int i)
    {

        int j = 0;
        do
        {

            g.setColor(j != i ? DayColor : j != 0 ? 
            		TodayColor : SundayColor);
            int k = 0;
            do
            {

                g.drawLine(WeekPointX[j][k * 2] + WeekOffset[j],
                 WeekPointY[j][k * 2], WeekPointX[j][k * 2 + 1] + 
                 WeekOffset[j], WeekPointY[j][k * 2 + 1]);
                k++;
 
            }
            while(WeekPointX[j][k * 2] != -1);

        }
        while(++j < 7);

    }
    //设置主窗口
    void drawFrame(Graphics g)
    {

        g.setColor(BGColor);
        g.fillRect(0, 0, base_width, base_height);
        if(ShowFrame)
        {
      
            g.setColor(BorderLight);
            g.drawRect(0, 0, base_width - 1, base_height - 1);
            g.drawRect(1, 1, base_width - 3, base_height - 3);
            g.drawRect(3, 3, base_width - 7, base_height - 7);
            g.drawRect(4, 4, base_width - 9, base_height - 9);
            g.setColor(BorderMideum);
            g.drawRect(2, 2, base_width - 5, base_height - 5);
            g.setColor(BorderDark);
            g.drawLine(base_width - 1, 1, base_width - 1, 
            		base_height - 1);
            g.drawLine(base_width - 1, base_height - 1, 0, 
            		base_height - 1);
            g.drawLine(base_width - 2, 2, base_width - 2, 
            		base_height - 2);
            g.drawLine(base_width - 2, base_height - 2, 1, 
            		base_height - 2);
            g.drawLine(3, 3, base_width - 4, 3);
            g.drawLine(3, 3, 3, base_height - 5);
            g.drawLine(4, 4, base_width - 5, 4);
            g.drawLine(4, 4, 4, base_height - 6);
       
        }
        int i = offset;
        if(ShowWeek)
            i += 10;
        g.drawImage(image[10], bdigit_width * 2 + offset, i, this);
        g.drawImage(image[10], bdigit_width * 5 + offset, i, this);
        if(ShowDate)
        {
        
            i += 20;
            g.setColor(BorderLight);
            g.drawLine(offset + 3, i + 2, base_width - offset - 4,
            		 i + 2);
            i += 5;
            g.drawImage(image[11], bdigit_width * 2 + offset,
            		 i, this);
            g.drawImage(image[11], bdigit_width * 5 + offset, 
            		i, this);
        
        }
    
    }
    //画屏函数
    public void paintApplet(Graphics g)
    {

        int i = offset;
        Date date = new Date();
        if(TimeDiff != 0L)
            date.setTime(date.getTime() + TimeDiff);
        int j = date.getYear();
        int k = date.getMonth() + 1;
        int l = date.getDate();
        int i1 = date.getHours();
        int j1 = date.getMinutes();
        int k1 = date.getSeconds();
        if(!Show24Hour)
            i1 = i1 <= 12 ? i1 : i1 - 12;
        if(buf_image == null)
            drawFrame(g);
        if(ShowWeek)
        {
 
            g.drawImage(wimage[date.getDay()], offset, i, this);
            i += 10;
 
        }
        g.drawImage(image[i1 / 10], offset, i, this);
        g.drawImage(image[i1 % 10], bdigit_width + offset, i, this);
        g.drawImage(image[j1 / 10], 
        		bdigit_width * 3 + offset, i, this);
        g.drawImage(image[j1 % 10], 
        		bdigit_width * 4 + offset, i, this);
        g.drawImage(image[k1 / 10], 
        		bdigit_width * 6 + offset, i, this);
        g.drawImage(image[k1 % 10], 
        		bdigit_width * 7 + offset, i, this);
        if(ShowDate)
        {

            i += 25;
            g.drawImage(image[k / 10], offset, i, this);
            g.drawImage(image[k % 10], bdigit_width + offset,
            		 i, this);
            g.drawImage(image[l / 10], bdigit_width * 3 + offset,
            		 i, this);
            g.drawImage(image[l % 10], bdigit_width * 4 + offset,
            		 i, this);
            g.drawImage(image[j / 10], bdigit_width * 6 + offset,
             		 i, this);
            g.drawImage(image[j % 10], bdigit_width * 7 + offset, 
            		i, this);
        
        }
    
    }
    //刷新屏幕函数
    public void update(Graphics g)
    {
       
        if(tracker.statusID(0, true) == 8)
        {
      
            if(buf_image != null)
            {
       
                paintApplet(gc);
                g.drawImage(buf_image, 0, 0, this);
                return;
       
            }
            g.clearRect(0, 0, base_width, base_height);
            paintApplet(g);
      
        }
   
    }
    //启动小程序
    public void start()
    {
 
        if(timer == null)
        {
  
            timer = new Thread(this, "JDClock");
            timer.start();
   
        }
   
    }
    //停止小程序
    public void stop()
    {
 
        timer = null;

    }
    //运行小程序
    public void run()
    {

        while(timer != null)
        {
 
            try
            {
 
                Thread.sleep(1000L);
                repaint();
                continue;

            }
            catch(InterruptedException _ex) { }
            break;

        }

    }

}