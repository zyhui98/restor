//AnimText.java
import java.awt.*;
import java.util.StringTokenizer;

public class AnimText extends java.applet.Applet implements Runnable {
    //变量定义
    public static final int TYPE_BLINK = 1;
    public static final int TYPE_WAVE = 2;
    public static final int TYPE_RANDOM = 3;

    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_RIGHT = 3;

    char textChars[];
    Thread thread;
    int type;
    int style;
    int defaultMin=8;
    int defaultMax=28;
    int max;
    int min;
    int defaultStep = 2;
    int step;
    int align;
    String rgbDelimiter = ":,.";
    StringTokenizer st;
    Color fgColor;
    Color bgColor;
    boolean threadSuspended = false;
    static final String defaultString = "Welcome to Java!";
    String fontString;
    Font fonts[];
    int current[];
    int direction[];
    //每一个字符的宽度
    int charWidth[];		
    //每一个字符的高度
    int charHeight;	
    boolean resized = false;
    boolean readyToPaint = true;
    int naptime;
    int defaultNaptime = 100;
    int Width;
    int Height;
    int defaultWidth = 300;
    int defaultHeight = 100;
    int maxWidth = 600;
    int maxHeight = 400;
    int n;
    Image offI;
    Graphics offG;

    int totalWidth;
    int leader = 10;
    //初始化小程序
    public void init() {
	String s;
	Integer intObj;

	s = getParameter("text");
	if (s == null)
	    s = defaultString;
	textChars =  new char[s.length()];
	s.getChars(0 , s.length(), textChars, 0);

	s = getParameter("font");
	if (s == null)
	    fontString = "TimesRoman";
	else if (s.equalsIgnoreCase("TimesRoman"))
	    fontString = "TimesRoman";
	else if (s.equalsIgnoreCase("Courier"))
	    fontString = "Courier";
	else if (s.equalsIgnoreCase("Helvetica"))
	    fontString = "Helvetica";
	else if (s.equalsIgnoreCase("Dialog"))
	    fontString = "Dialog";
	else
	    fontString = "TimesRoman";

	s = getParameter("style");
	if (s == null)
	    style = Font.PLAIN;
	else if (s.equalsIgnoreCase("PLAIN"))
	    style = Font.PLAIN;
	else if (s.equalsIgnoreCase("BOLD"))
	    style = Font.BOLD;
	else if (s.equalsIgnoreCase("ITALIC"))
	    style = Font.ITALIC;
	else
	    style = Font.PLAIN;

	s = getParameter("type");
	if (s == null)
	    type = TYPE_WAVE;
	else if (s.equalsIgnoreCase("blink"))
	    type = TYPE_BLINK;
	else if (s.equalsIgnoreCase("wave"))
	    type = TYPE_WAVE;
	else if (s.equalsIgnoreCase("random"))
	    type = TYPE_RANDOM;
	else
	    type = TYPE_WAVE;

	s = getParameter("align");
	if (s == null)
	    align = ALIGN_CENTER;
	else if (s.equalsIgnoreCase("left"))
	    align = ALIGN_LEFT;
	else if (s.equalsIgnoreCase("center"))
	    align = ALIGN_CENTER;
	else if (s.equalsIgnoreCase("right"))
	    align = ALIGN_RIGHT;
	else
	    align = ALIGN_CENTER;

	try {
	    intObj = new Integer(getParameter("width"));
	    Width = intObj.intValue();
	} catch (Exception e) {
	    Width = defaultWidth;
	}

	try {
	    intObj = new Integer(getParameter("height"));
	    Height = intObj.intValue();
	} catch (Exception e) {
	    Height = defaultHeight;
	}

	try {
	    intObj = new Integer(getParameter("min"));
	    min = intObj.intValue();
	} catch (Exception e) {
	    min = defaultMin;
	}

	try {
	    intObj = new Integer(getParameter("max"));
	    max = intObj.intValue();
	} catch (Exception e) {
	    max = defaultMax;
	}
	if (min >= max || min <= 0) {
	    min = defaultMin;
	    max = defaultMax;
	}

	try {
	    intObj = new Integer(getParameter("step"));
	    step = intObj.intValue();
	} catch (Exception e) {
	    step = defaultStep;
	}
	if (step > (max-min)/2) step = defaultStep;

	try {
	    intObj = new Integer(getParameter("naptime"));
	    naptime = intObj.intValue();
	} catch (Exception e) {
	    naptime = defaultNaptime;
	}
	if (naptime <= 0) naptime = defaultNaptime;

	s = getParameter("fgColor");
	if (s != null) st = new StringTokenizer(s, rgbDelimiter);

	if (s == null)
	    fgColor = Color.black;
	else if (s.equalsIgnoreCase("red"))
	    fgColor = Color.red;
	else if (s.equalsIgnoreCase("blue"))
	    fgColor = Color.blue;
	else if (s.equalsIgnoreCase("green"))
	    fgColor = Color.green;
	else if (s.equalsIgnoreCase("yellow"))
	    fgColor = Color.yellow;
	else if (s.equalsIgnoreCase("white"))
	    fgColor = Color.white;
	else if (s.equalsIgnoreCase("orange"))
	    fgColor = Color.orange;
	else if (s.equalsIgnoreCase("cyan"))
	    fgColor = Color.cyan;
	else if (s.equalsIgnoreCase("magenta"))
	    fgColor = Color.magenta;
	else if (st.countTokens() == 3) {
	    Integer r = new Integer(st.nextToken());
	    Integer g = new Integer(st.nextToken());
	    Integer b = new Integer(st.nextToken());
	    fgColor = new Color(r.intValue(), g.intValue(), b.intValue());
	} else
	    fgColor = Color.black;

	s = getParameter("bgColor");
	if (s != null) st = new StringTokenizer(s, rgbDelimiter);

	if (s == null)
	    bgColor = Color.lightGray;
	else if (s.equalsIgnoreCase("red"))
	    bgColor = Color.red;
	else if (s.equalsIgnoreCase("blue"))
	    bgColor = Color.blue;
	else if (s.equalsIgnoreCase("green"))
	    bgColor = Color.green;
	else if (s.equalsIgnoreCase("yellow"))
	    bgColor = Color.yellow;
	else if (s.equalsIgnoreCase("white"))
	    bgColor = Color.white;
	else if (s.equalsIgnoreCase("orange"))
	    bgColor = Color.orange;
	else if (s.equalsIgnoreCase("cyan"))
	    bgColor = Color.cyan;
	else if (s.equalsIgnoreCase("magenta"))
	    bgColor = Color.magenta;
	else if (st.countTokens() == 3) {
	    Integer r = new Integer(st.nextToken());
	    Integer g = new Integer(st.nextToken());
	    Integer b = new Integer(st.nextToken());
	    bgColor = new Color(r.intValue(), g.intValue(), b.intValue());
	} else
	    bgColor = Color.lightGray; 

	n = max-min;
	if (n>0) {
	    fonts = new Font[n];
	    current = new int[textChars.length];
	    direction = new int[textChars.length];
	    charWidth = new int[textChars.length];
	}
	for (int i=0; i<n; i++) {
	    fonts[i] = new Font(fontString, style, min+i);          
	}
	for (int i=0; i<textChars.length; i++) {
	    switch (type) {
	    case TYPE_BLINK:
		current[i] = 0;
		direction[i] = 1;
		break;
	    case TYPE_WAVE:
		current[i] = (int)(Math.sin((double)i/(double)textChars.length*Math.PI)*(float)(n-1));
		direction[i] = 1;
		break; 
	    case TYPE_RANDOM:
	        current[i] = (int)(Math.random()*(float)(n));
		direction[i] = 1;
		break;
	    default:
	    }
	    if (current[i] >= n-1) direction[i] = -1;
	}

        //屏幕图形内容
	try {
	    offI = createImage(maxWidth, maxHeight);
	    offG = offI.getGraphics();
	} catch (Exception e) {
	    offG = null;
	}
    }

    public void start() {
	if (thread == null) {
	    thread = new Thread(this);
	    thread.start();
	}
    }

    public void run() {
	while ((n>0) && (thread != null)) {
	    repaint();
	    try { Thread.sleep(naptime); } catch (Exception e) { }
	    readyToPaint = false;
	    next();
	    readyToPaint = true;
	}
    }

    //下一个字符
    public void next() {
        for (int i=0; i<textChars.length; i++) {
	    current[i] += step*direction[i];
	    if (current[i] >= n-1) {
                current[i] = 2*n-2-current[i];
                direction[i] = -1;
            }
            if (current[i] <= 0) {
                current[i] = Math.abs(current[i]);
                direction[i] = 1;
            }
	}
    }

    //重载update函数
    public void update(Graphics g) {
	if (readyToPaint)
	    paint(g);
    }
    //画屏函数
    public void paint(Graphics g) {
	if (offG != null) {
	    paintApplet(offG);
	    g.drawImage(offI, 0, 0, this);
	} else {
	    paintApplet(g);
	}
    }
    //画图
    public void paintApplet(Graphics g) {
	if (!resized) {
	    totalWidth = 0;
	    //最大字体
	    g.setFont(fonts[n-1]); 
	    for (int i=0; i<textChars.length; i++) {
		charWidth[i] = g.getFontMetrics().charWidth(textChars[i]);
		totalWidth += charWidth[i];
	    }
	    if (totalWidth>maxWidth) totalWidth = maxWidth;
	    charHeight = g.getFontMetrics().getHeight();
	    if (charHeight>maxHeight) charHeight = maxHeight;
	    resize(Width, Height);
	    resized = true;
	}

	int pos = 0;

	switch (align) {
	case ALIGN_LEFT:
	    pos = leader; break;
	case ALIGN_CENTER:
	    pos = (size().width-totalWidth)/2; break;
	case ALIGN_RIGHT:
	    pos = (size().width-totalWidth-leader); break;
	default:
	}

	g.setColor(bgColor);
	g.fillRect(0, 0, size().width-1, size().height-1);
	g.setColor(fgColor);
	for(int i=0; i<textChars.length; i++) {
	    g.setFont(fonts[current[i]]);
	    g.drawChars(textChars, i, 1, pos, (size().height+charHeight)/2);
	    pos += charWidth[i];
	}
    }
    //停止小程序
    public void stop() {
	thread = null;
    }
    //响应按下鼠标
    public boolean mouseDown(Event e, int x, int y) {
        if (threadSuspended)
            thread.resume();
        else
            thread.suspend();
        threadSuspended = !threadSuspended;
	return true;
    }
}