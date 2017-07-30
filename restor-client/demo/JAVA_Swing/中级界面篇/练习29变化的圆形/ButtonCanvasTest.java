//ButtonCanvasTest.java
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class ButtonCanvasTest extends Applet implements ActionListener
   {
    Button bt1=new Button("空心圆");
    Button bt2=new Button("实心圆");
    koCanvas pic=new koCanvas();
    //初始化函数
    public void init()
	{
	add(bt1);
	add(bt2);
    add(pic);
    bt1.setBackground(Color.orange);	   	   
	bt2.setBackground(Color.orange);
	bt1.addActionListener(this);
	bt2.addActionListener(this);
	}
    //消息监听函数
   public void actionPerformed(ActionEvent e)
	{
	if (e.getSource()==bt1)
	   pic.picture1();
	else if (e.getSource()==bt2)
	   pic.picture2();
	pic.repaint();
	}
   }
//派生一个画布类
class koCanvas extends Canvas
   {
   boolean choose;
   public koCanvas()
      {
      setSize(170,120);
      }
   public void picture1()
      {
      choose=true;
      }
   public void picture2()
      {
      choose=false;
      }
   //画屏函数
   public void paint(Graphics g)
      {
       if (choose)
	  {
	  g.setColor(Color.red);
	  g.drawOval(40,10,40,50);
          g.drawString("空心圆",40,80);
          }
       else
	  {
	  g.setColor(Color.blue);
	  g.fillOval(40,10,40,50);
	  g.drawString("实心圆",40,80);
	  }
      }
   }    