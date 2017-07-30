//Cubic.java
import java.awt.*;
import java.awt.geom.*;

public class Cubic extends ExitableJFrame{
	//画屏函数
        public void paint(Graphics g){
        	//实例化Graphics2D类
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		//设置颜色
		g2d.setPaint(Color.blue);
		g2d.setStroke(new BasicStroke(3));
		//CubicCurve2D
		CubicCurve2D cubic = new CubicCurve2D.Float(10,80,60,30,110,130,160,80);
		g2d.draw(cubic);
		cubic = new CubicCurve2D.Float(60,205,150,120,20,120,110,205);
		g2d.fill(cubic);
		cubic = new CubicCurve2D.Float(160,130,210,30,260,30,310,130);
		g2d.fill(cubic);
		cubic = new CubicCurve2D.Float(160,130,260,230,210,230,310,130);
		g2d.fill(cubic);
		//设置颜色
		g.setColor(Color.black);
		//填充颜色
		g.fillRect(58,28,5,5);
		g.fillRect(108,128,5,5);
		g.fillRect(148,118,5,5);
		g.fillRect(18,118,5,5);
		g.fillRect(208,28,5,5);
		g.fillRect(258,28,5,5);
		g.fillRect(258,228,5,5);
		g.fillRect(208,228,5,5);
	}
	//主函数
	public static void main(String args[]){
		Frame f = new Cubic();
		f.setTitle("Cubic");
		f.setSize(350,350);
		f.show();
	}
}