//Mousetest2.java
//主要功能是当移动鼠标时，显示鼠标的坐标位置，
//当拖拽鼠标时，画一个直线。
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Event;
import java.applet.Applet;
import java.awt.Color;

public class Mousetest2 extends Applet  {
  final int MaxMarks=40;
  int CurMarks=0;
  Point MarkStarts[]=new Point[MaxMarks];
  Point MarkEnds[]=new Point[MaxMarks];
  Point StartPoint,CurPoint;
  String CurMessage="";

  //按下鼠标按键时调用的方法。
  public boolean mouseDown(Event evt,int x,int y)  {
   if(CurMarks<MaxMarks) {
    StartPoint=new Point(x,y);
   }
   return true;
  }

  //松开鼠标按键时调用的方法。
  public boolean mouseUp(Event evt,int x,int y) {
   if(CurMarks<MaxMarks) {
    MarkStarts[CurMarks]=StartPoint;
    MarkEnds[CurMarks]=CurPoint;
    CurMarks++;
    StartPoint=null;
    CurPoint=null;
    repaint();
   }
   return true;
  }

  //当拖拽鼠标时调用的方法。
  public boolean mouseDrag(Event evt,int x,int y) {
   if(CurMarks<MaxMarks) {
    CurPoint=new Point(x,y);
    repaint();
    }
    return true;
  }

  //当移动鼠标时调用的方法。
  public boolean mouseMove(Event evt,int x,int y) {
   CurMessage="鼠标的坐标位置为（"+x+","+y+"）";
   repaint();
   return true;
  }

 public void paint(Graphics g) {
  int x1,y1,x2,y2;
  g.drawString(CurMessage,10,10);
  for(int i=0;i<CurMarks;i++) {
   x1=MarkStarts[i].x;
   y1=MarkStarts[i].y;
   x2=MarkEnds[i].x;
   y2=MarkEnds[i].y;
   g.setColor(Color.blue);
   g.drawLine(x1,y1,x2,y2);
  }
  if(StartPoint!=null) {
   x1=StartPoint.x;
   y1=StartPoint.y;
   x2=CurPoint.x;
   y2=CurPoint.y;
   g.setColor(Color.blue);
   g.drawLine(x1,y1,x2,y2);
  }
 }
}
