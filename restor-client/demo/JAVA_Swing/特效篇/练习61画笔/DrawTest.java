//DrawTest.java
import java.awt.*;
import java.applet.*;
import java.util.Vector;

public class DrawTest extends Applet {
    //初始化函数	
    public void init() {
	setLayout(new BorderLayout());
	DrawPanel dp = new DrawPanel();
	add("Center", dp);
	add("South",new DrawControls(dp));
    }
    //处理窗口事件
    public boolean handleEvent(Event e) {
	switch (e.id) {
	  case Event.WINDOW_DESTROY:
	    System.exit(0);
	    return true;
	  default:
	    return false;
	}
    }
    //主函数
    public static void main(String args[]) {
	Frame f = new Frame("我的画笔");
	DrawTest drawTest = new DrawTest();
	drawTest.init();
	drawTest.start();

	f.add("Center", drawTest);
	f.resize(300, 300);
	f.show();
    }
}
//从面板类继承出DrawPanel类
class DrawPanel extends Panel {
    public static final int LINES = 0;
    public static final int POINTS = 1;
    int	   mode = LINES;
    Vector lines = new Vector();
    Vector colors = new Vector();
    int x1,y1;
    int x2,y2;
    int xl, yl;
    //构造函数
    public DrawPanel() {
	setBackground(Color.white);
    }
    //设置画图模式
    public void setDrawMode(int mode) {
	switch (mode) {
	  case LINES:
	  case POINTS:
	    this.mode = mode;
	    break;
	  default:
	    throw new IllegalArgumentException();
	}
    }
    //响应窗口事件
    public boolean handleEvent(Event e) {
	switch (e.id) {
	  case Event.MOUSE_DOWN:
	    switch (mode) {
	      case LINES:
		x1 = e.x;
		y1 = e.y;
		x2 = -1;
		break;
	      case POINTS:
	      default:
		colors.addElement(getForeground());
		lines.addElement(new Rectangle(e.x, e.y, -1, -1));
		x1 = e.x;
		y1 = e.y;
		repaint();
		break;
	    }
	    return true;
	  case Event.MOUSE_UP:
	    switch (mode) {
	      case LINES:
		colors.addElement(getForeground());
		lines.addElement(new Rectangle(x1, y1, e.x, e.y));
		x2 = xl = -1;
		break;
	      case POINTS:
	      default:
		break;
	    }
	    repaint();
	    return true;
	  case Event.MOUSE_DRAG:
	    switch (mode) {
	      case LINES:
		xl = x2;
		yl = y2;
		x2 = e.x;
		y2 = e.y;
		break;
	      case POINTS:
	      default:
		colors.addElement(getForeground());
		lines.addElement(new Rectangle(x1, y1, e.x, e.y));
		x1 = e.x;
		y1 = e.y;
		break;
	    }
	    repaint();
	    return true;
	  case Event.WINDOW_DESTROY:
	    System.exit(0);
	    return true;
	  default:
	    return false;
	}
    }
    //画屏函数
    public void paint(Graphics g) {
	int np = lines.size();
	//画线
	g.setColor(getForeground());
	g.setPaintMode();
	for (int i=0; i < np; i++) {
	    Rectangle p = (Rectangle)lines.elementAt(i);
	    g.setColor((Color)colors.elementAt(i));
	    if (p.width != -1) {
		g.drawLine(p.x, p.y, p.width, p.height);
	    } else {
		g.drawLine(p.x, p.y, p.x, p.y);
	    }
	}
	if (mode == LINES) {
	    g.setXORMode(getBackground());
	    if (xl != -1) {
		g.drawLine(x1, y1, xl, yl);
	    }
	    g.setColor(getForeground());
	    g.setPaintMode();
	    if (x2 != -1) {
		g.drawLine(x1, y1, x2, y2);
	    }
	}
    }
}

//画图控制面板
class DrawControls extends Panel {
    DrawPanel target;

    public DrawControls(DrawPanel target) {
	this.target = target;
	setLayout(new FlowLayout());
	setBackground(Color.lightGray);
	target.setForeground(Color.red);
	CheckboxGroup group = new CheckboxGroup();
	Checkbox b;
	add(b = new Checkbox(null, group, false));
	b.setBackground(Color.red);
	add(b = new Checkbox(null, group, false));
	b.setBackground(Color.green);
	add(b = new Checkbox(null, group, false));
	b.setBackground(Color.blue);
	add(b = new Checkbox(null, group, false));
	b.setBackground(Color.pink);
	add(b = new Checkbox(null, group, false));
	b.setBackground(Color.orange);
	add(b = new Checkbox(null, group, true));
	b.setBackground(Color.black);
	target.setForeground(b.getForeground());
	Choice shapes = new Choice();
	shapes.addItem("Lines");
	shapes.addItem("Points");
	shapes.setBackground(Color.lightGray);
	add(shapes);
    }
    //画图函数
    public void paint(Graphics g) {
	Rectangle r = bounds();

	g.setColor(Color.lightGray);
	g.draw3DRect(0, 0, r.width, r.height, false);
    }
    //响应动作
    public boolean action(Event e, Object arg) {
	if (e.target instanceof Checkbox) {
	    target.setForeground(((Component)e.target).getBackground());
	} else if (e.target instanceof Choice) {
	    String choice = (String)arg;

	    if (choice.equals("Lines")) {
		target.setDrawMode(DrawPanel.LINES);
	    } else if (choice.equals("Points")) {
		target.setDrawMode(DrawPanel.POINTS);
	    }
	}
	return true;
    }
}