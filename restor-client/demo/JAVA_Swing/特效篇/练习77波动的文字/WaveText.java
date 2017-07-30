//WaveText.java
import java.awt.*;

public class WaveText extends java.applet.Applet
    implements Runnable {
  //变量定义
  String str = null;
  int direction = 1;
  int horizontalRadius = 10;
  int verticalRadius = 10;
  Thread runner = null;
  char theChars[];
  int phase = 0;
  Image offScreenImage;
  Graphics offScreenG;

//初始化小程序
public void init() {
  String paramStr = null;
  str = getParameter("text");
  paramStr = getParameter("direction");
  setBackground(Color.black);
  if (paramStr != null)
    direction = Integer.parseInt(paramStr);
  paramStr = getParameter("horizontalRadius");
  if (paramStr != null)
    horizontalRadius = Integer.parseInt(paramStr);
  paramStr = getParameter("verticalRadius");
  if (paramStr != null)
    verticalRadius = Integer.parseInt(paramStr);
  setFont(new Font("TimesRoman",Font.BOLD,36));
  if (str == null) {
	str = "Museum of Java Applets";
  }
  resize(30+25*str.length()+2*horizontalRadius,80+2*verticalRadius);

  theChars =  new char [str.length()];
  str.getChars(0,str.length(),theChars,0);
  offScreenImage = createImage(this.size().width,this.size().height);
  offScreenG = offScreenImage.getGraphics();
  offScreenG.setFont(new Font("TimesRoman",Font.BOLD,36));
 }
 
//启动小程序
public void start() {
  if(runner == null) {
    runner = new Thread(this);
    runner.start();
  }
}

//停止小程序
public void stop() {
  if (runner != null) {
    runner.stop();
    runner = null;
  }
}

//运行小程序
public void run() {
  while (runner != null) {
	try {
	  Thread.sleep(120);
	}
	catch (InterruptedException e) { }
	repaint();
  }
}

  //更新界面
  public void update(Graphics g) {
    int x, y;
    double angle;
    offScreenG.setColor(Color.black);
    offScreenG.fillRect(0,0,this.size().width,this.size().height);
    phase+=direction;
    phase%=8;
    for(int i=0;i<str.length();i++) {
  	  angle = ((phase-i*direction)%8)/4.0*Math.PI;
  	  //水平方向的运动
	  x = 20+25*i+(int) (Math.cos(angle)*horizontalRadius); 
	  //竖直方向的运动
	  y = 60+  (int) (Math.sin(angle)*verticalRadius); 
      // 第一个字的颜色是蓝色
      if (i==0 || theChars[i-1]==' ')  
        offScreenG.setColor(Color.blue);
      else
        //其它字为红色
        offScreenG.setColor(Color.orange);  
	  offScreenG.drawChars(theChars,i,1,x,y);
	}
    paint(g);
  }

  //画屏函数
  public void paint(Graphics g) {
    g.drawImage(offScreenImage,0,0,this);
  }
}