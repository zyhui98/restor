//NervousText.java
import java.awt.Graphics;
import java.awt.Font;

public class NervousText extends java.applet.Applet implements Runnable {
        
    //变量定义
	char separated[];
	String s = null;
	Thread killme = null;
	int i;
	int x_coord = 0, y_coord = 0;
	String num;
	int speed=35;
	int counter =0;
	boolean threadSuspended = false; 
//初始化小程序
public void init() {
	//设置显示的文字
	s = getParameter("text");
	if (s == null) {
	    s = "I Like Java";
	}
        //设置分割
	separated =  new char [s.length()];
	s.getChars(0,s.length(),separated,0);
	resize(150,50);
	//设置字体
	setFont(new Font("TimesRoman",Font.BOLD,36));
 }
//启动小程序
public void start() {
	if(killme == null) 
	{
        killme = new Thread(this);
        killme.start();
	}
 }
//停止小程序
public void stop() {
	killme = null;
 }
//运行小程序
public void run() {
	while (killme != null) {
	try {Thread.sleep(100);} catch (InterruptedException e){}
	repaint();
	}
	killme = null;
 }
//画屏函数
public void paint(Graphics g) {
	for(i=0;i<s.length();i++)
	{
	x_coord = (int) (Math.random()*10+15*i);
	y_coord = (int) (Math.random()*10+36);
	g.drawChars(separated, i,1,x_coord,y_coord);
	}
 }
 
//响应按下鼠标
public boolean mouseDown(java.awt.Event evt, int x, int y) {
        if (threadSuspended) {
            killme.resume();
        }
        else {
            killme.suspend();
        }
        threadSuspended = !threadSuspended;
    return true;
    }
}