//stn_bump.java
import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.image.*;
import java.net.MalformedURLException;
import java.net.URL;

public class stn_bump extends Applet
    implements Runnable
{
    //变量定义
    private String m_bmColor = null;
    private String m_bmBump = null;
    private String m_link = null;
    private String m_descr = null;
    private int m_garbage = 0;
    private int m_sleep = 0;
    private int m_hred = 0;
    private int m_hgreen = 0;
    private int m_hblue = 0;
    private int m_hexp = 0;
    private final String PARAM_bmColor = "color";
    private final String PARAM_bmBump = "bump";
    private final String PARAM_link = "link";
    private final String PARAM_descr = "descr";
    private final String PARAM_garbage = "garbage";
    private final String PARAM_sleep = "sleep";
    private final String PARAM_hred = "hred";
    private final String PARAM_hgreen = "hgreen";
    private final String PARAM_hblue = "hblue";
    private final String PARAM_hexp = "hexp";
    Dimension m_dimWindow = null;
    boolean m_bImgLoaded = false;
    Image m_imgColor = null;
    Image m_imgBump = null;
    Thread m_animator = null;
    ColorModel m_colormodel = null;
    Dimension m_dimBitmap = null;
    int m_rawColor[] = null;
    int m_rawBump[] = null;
    int m_rawPhong[] = null;
    int m_offsetBump[] = null;
    int m_bitmap[] = null;
    boolean m_bMouseMove = false;
    int m_iCenterX = 0;
    int m_iCenterY = 0;
    //启动小程序
    public void start()
    {
    	
        if(m_animator == null)
        {
        	
            m_animator = new Thread(this);
            m_animator.setPriority(10);
            m_animator.start();
            
        }
        
    }
    //停止小程序    
    public void stop()
    {
    	
            m_animator = null;
            
    }
    //响应鼠标进入
    public boolean mouseEnter(Event event, int i, int j)
    {
    	
        if(m_animator != null)
        {
        	
            showStatus(m_descr);
            m_bMouseMove = true;
            
        }
        return true;
        
    }
    //响应鼠标退出
    public boolean mouseExit(Event event, int i, int j)
    {
    	
        if(m_animator != null)
        {
        	
            showStatus("");
            m_bMouseMove = false;
            
        }
        return true;
        
    }
    //响应鼠标按下
    public boolean mouseDown(Event event, int i, int j)
    {
    	
        if(m_animator != null && m_link != null)
        {
        	
            URL url;
            try
            {
            	
                url = new URL(m_link);
                
            }
            catch(MalformedURLException _ex)
            {
            	
                showStatus("URL " + m_link + " is invalid!");
                return true;
                
            }
            getAppletContext().showDocument(url);
            
        }
        return true;
        
    }
    //画屏函数
    public void paint(Graphics g)
    {
    	
        if(m_animator != null)
        {
        	
            MemoryImageSource memoryimagesource = 
            new MemoryImageSource(m_dimBitmap.width, 
                                  m_dimBitmap.height, 
                                  m_colormodel, 
                                  m_bitmap, 
                                  0, 
                                  m_dimBitmap.width);
            Image image = createImage(memoryimagesource);
            g.drawImage(image, 0, 0, m_dimWindow.width, 
                                     m_dimWindow.height, this);
        }
        
    }
    //刷新图片
    public boolean imageUpdate(Image image, int i, int j, 
                               int k, int l, int m)
    {
    	
        return true;
        
    }
    //运行小程序
    public void run()
    {
    	
        float f3 = 0.0F;
        float f4 = 0.0F;
        if(!m_bImgLoaded)
        {
        	
            repaint();
            MediaTracker mediatracker = new MediaTracker(this);
            m_imgColor = getImage(getDocumentBase(), m_bmColor);
            m_imgBump = getImage(getDocumentBase(), m_bmBump);
            mediatracker.addImage(m_imgColor, 0);
            mediatracker.addImage(m_imgBump, 0);
            try
            {
            	
                mediatracker.waitForAll();
                m_bImgLoaded = !mediatracker.isErrorAny();
                
            }
            catch(InterruptedException _ex) { }
            if(!m_bImgLoaded)
            {
            	
                stop();
                getGraphics().drawString("Error loading images!", 
                                         10, 40);
                return;
                
            }
            if(m_imgColor.getHeight(this)!=m_imgBump.getHeight(this)
              || m_imgColor.getWidth(this) != m_imgBump.getWidth(this))
            {
            	
                stop();
                getGraphics().drawString(
                          "Images must have same extends!", 10, 40);
                return;
                
            }
            m_dimBitmap = new Dimension(m_imgColor.getWidth(this), 
                                        m_imgColor.getHeight(this));
            m_bitmap=new int[m_dimBitmap.width * 
                             m_dimBitmap.height];
            m_rawColor=new int[m_dimBitmap.width * 
                               m_dimBitmap.height];
            PixelGrabber pixelgrabber = new PixelGrabber(m_imgColor,
                                            0, 0, 
                                            m_dimBitmap.width, 
                                            m_dimBitmap.height, 
                                            m_rawColor, 
                                            0, m_dimBitmap.width);
            try
            {
            	
                pixelgrabber.grabPixels();
                
            }
            catch(InterruptedException _ex)
            {
            	
                stop();
                getGraphics().drawString(
                  "Error retrieving image data of " + m_bmColor + 
                  "!", 10, 40);
                return;
                
            }
            m_rawBump = new int[m_dimBitmap.width * 
                                m_dimBitmap.height];
            PixelGrabber pixelgrabber1 = new PixelGrabber(m_imgBump, 
                                             0, 0, 
                                             m_dimBitmap.width, 
                                             m_dimBitmap.height, 
                                             m_rawBump, 
                                             0, m_dimBitmap.width);
            try
            {
            	
                pixelgrabber1.grabPixels();
                
            }
            catch(InterruptedException _ex)
            {
            	
                stop();
                getGraphics().drawString(
                "Error retrieving image data of " + 
                m_bmBump + "!", 10, 40);
                return;
                
            }
            m_offsetBump = new int[m_dimBitmap.width * 
                                   m_dimBitmap.height];
            for(int p_row = 1; 
                p_row < m_dimBitmap.height - 1; 
                p_row++)
            {
            	
                int p_col = p_row * m_dimBitmap.width + 1;
                for(int i = 1; i < m_dimBitmap.width - 1; i++)
                {
                	
                    int pBump1=((m_rawBump[p_col - 1] & 0xff) >> 1)
                                   - ((m_rawBump[p_col + 1] 
                                      & 0xff) >> 1);
                    int pBump2 = ((m_rawBump[p_col - 
                                   m_dimBitmap.width] & 0xff) >> 1) 
                                   - ((m_rawBump[p_col + 
                                       m_dimBitmap.width] 
                                       & 0xff) >> 1);
                    m_offsetBump[p_col] = (pBump1 & 0xff) + 
                                          ((pBump2 & 0xff) << 8);
                    p_col++;
                    
                }

            }

            m_rawPhong = new int[0x10000];
            int base1 = 0;
            do
            {
            	
                int base2 = base1 * 256;
                int j = 0;
                do
                {
                	
                    float edge1 = ((float)j - 128F) / 128F;
                    edge1 *= edge1;
                    float edge2 = ((float)base1 - 128F) / 128F;
                    edge2 *= edge2;
                    float light_color;
                    if(edge1 + edge2 > 1.0F)
                        light_color = 0.0F;
                    else
                        light_color = 
                        (float)Math.pow(Math.cos((Math.sqrt(edge1 
                                + edge2) * Math.PI) / 2D), m_hexp);
                    int setRed = (int)(light_color * 
                                 (float)m_hred);
                    int setGreen = (int)(light_color * 
                                   (float)m_hgreen);
                    int setBlue = (int)(light_color * 
                                  (float)m_hblue);
                    if(setRed < 8 && setGreen < 8 && setBlue < 8)
                    {
                    	
                        setRed = 0;
                        setGreen = 0;
                        setBlue = 0;
                        
                    }
                    m_rawPhong[base2] = 
                        setBlue + (setGreen << 8) + (setRed << 16);
                    base2++;
                    
                }
                while(++j < 256);
                
            }
            while(++base1 < 256);
            
        }
        repaint();
        int i5 = m_garbage;
        do
        {
        	
            do
            {
            	
                if(!m_bMouseMove)
                {
                	
                    m_iCenterX = (int)(128D - ((double)
                    ((float)m_dimBitmap.width / 2.0F) + 
                    (Math.sin(f3) * 
                    (double)(float)m_dimBitmap.width) / 2D) / 2D);
                    m_iCenterY = (int)(128D - ((double)
                    ((float)m_dimBitmap.height / 2.0F) + 
                    (Math.sin(f4) * 
                    (double)(float)m_dimBitmap.height) / 2D) / 2D);
                    f3 += 0.04846F;
                    f4 += 0.06145F;
                    
                }
                for(int p_row=0;p_row<m_dimBitmap.height;p_row++)
                {
                    
                    int light_x = p_row * m_dimBitmap.width;
                    int light_y = (p_row / 2 + m_iCenterY) * 256 + 
                                   m_iCenterX;
                    for(int p_col=0; p_col<m_dimBitmap.width;p_col++)
                    {
                    	
                        int pColor = m_rawColor[light_x];
                        int pPhong = m_rawPhong[light_y + p_col / 2 
                                 + m_offsetBump[light_x] & 0xffff];
                        if(pPhong != 0)
                        {
                        	
                            int cRed = (pColor & 0xff0000) + 
                                       (pPhong & 0xff0000);
                            int cGreen = (pColor & 0xff00) + 
                                         (pPhong & 0xff00);
                            int cBlue = (pColor & 0xff) + 
                                        (pPhong & 0xff);
                            if(cRed > 0xff0000)
                                cRed = 0xff0000;
                            if(cGreen > 65280)
                                cGreen = 65280;
                            if(cBlue > 255)
                                cBlue = 255;
                            m_bitmap[light_x]=cRed|cGreen|cBlue;
                            
                        }
                        else
                        {

                            m_bitmap[light_x] = pColor;
                            
                        }
                        light_x++;
                        
                    }

                }

                paint(getGraphics());
                if(--i5 < 0)
                {
                	
                    System.gc();
                    i5 = m_garbage;
                    
                }
                
            }
            while(m_sleep <= 0 || m_animator == null);
            try
            {
            	
                Thread.sleep(m_sleep);
                
            }
            catch(InterruptedException _ex)
            {
            	
                stop();
                return;
                
            }
            
        }
        while(true);
        
    }

    //得到一个字符串和一个整数型的参数
    int getParameter(String s1, int s2) 
    {

        String s = getParameter(s1) ;
        return (s != null) ? Integer.parseInt(s) : s2 ;
        
    }
    //得到两个字符串的参数
    String getParameter(String s1, String s2) 
    {
    	
        String s = getParameter(s1) ;
        return (s != null) ? s : s2 ;
    
    }
    //初始化小程序            
    public void init()
    {
    	
        m_bmColor = getParameter("color","color.gif");
        m_bmBump = getParameter("bump","bump.gif");        
        m_link = getParameter("link","localhost");
        m_descr = getParameter("descr","Hello World");
        m_garbage = getParameter("garbage",16);
        m_sleep = getParameter("sleep",20);
        m_hred = getParameter("hred",32);
        m_hgreen = getParameter("hgreen",192);
        m_hblue = getParameter("hblue",192);
        m_hexp = getParameter("hexp",8);
        m_dimWindow = new Dimension(getSize().width, 
                                    getSize().height);
        resize(m_dimWindow);
        m_colormodel = new DirectColorModel(
                           24, 0xff0000, 65280, 255);
                           
    }
    //响应拖动鼠标
    public boolean mouseDrag(Event event, int i, int j)
    {
    	
        return mouseMove(event, i, j);
        
    }
    //响应移动鼠标
    public boolean mouseMove(Event event, int i, int j)
    {
    	
        if(m_animator != null)
        {
        	
            m_iCenterX = 128 - (i * m_dimBitmap.width) / 
                                    m_dimWindow.width / 2;
            m_iCenterY = 128 - (j * m_dimBitmap.height) / 
                                    m_dimWindow.height / 2;
        
        }
        return true;
    
    }
}