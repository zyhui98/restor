//CircleCanvas.java
import java.awt.*;
//创建一个画布
public class CircleCanvas extends Canvas
{
     Color circleColor;
     //设置颜色接口
     public CircleCanvas(Color drawColor)
     {
          circleColor = drawColor;
     }
     //画屏函数
     public void paint(Graphics g)
     {
          int circleDiameter, circleX, circleY;
          Dimension currentSize = getSize();

          if (currentSize.width < currentSize.height)
          {
               circleDiameter = currentSize.width;
          }
          else
          {
               circleDiameter = currentSize.height;
          }
          g.setColor(circleColor);
          //设置横纵坐标
          circleX = (currentSize.width - circleDiameter) / 2;
          circleY = (currentSize.height - circleDiameter) / 2;

          g.fillOval(circleX, circleY, circleDiameter, circleDiameter);
     }
}
