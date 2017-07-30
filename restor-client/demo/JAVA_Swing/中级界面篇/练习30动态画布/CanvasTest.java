import java.applet.*;
import java.awt.*;
public class CanvasTest extends Applet implements Runnable
   {
    Canvas c=new Canvas();
    int x=0,y;
    double s;
    public void init()
	{
	c.setBackground(Color.green);
	c.setSize(160,80);
    	add(c);
	}
    public void start()
	{
	Thread ko_thread=new Thread(this);
	ko_thread.start();
	}
    public void run()
	{
	while(true)
	  {
	     repaint();
	     x+=10;
	     if (x==350)
		x=0;
	     try
	       {
	       Thread.sleep(100);
	       }
	     catch(InterruptedException e)
	       { }
	   }
	}
    public void paint(Graphics g)
	{
	s=Math.sin(x*Math.PI/180);
	y=(int)(60+40*s);
	g.setColor(Color.red);
	g.fillRoundRect(x,y,50,50,50,50);
	}
    }
