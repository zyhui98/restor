//BallAnim.java
import java.awt.*;
import java.applet.*;
import java.lang.*;

public class BallAnim extends Applet implements Runnable
{
   Thread animThread;
   //小球的横坐标
   int ballX = 0;     
   //小球的运动方向，0为从左到右
   int ballDirection = 0; 
   //启动小程序
   public void start()
   {
     if (animThread == null)
     {
        animThread = new Thread(this);
        animThread.start();
     }
   }
//停止小程序
   public void stop()
   {
     animThread.stop();
     animThread = null;
   }
//运行小程序
   public void run()
   {
     Thread.currentThread().setPriority(Thread.NORM_PRIORITY);

     while (true)
     {
        moveBall();
        try {
          // 睡眠 0.1 秒
          Thread.sleep(100);   
        } catch (Exception sleepProblem) {
        }
     }
   }

   private void moveBall()
   {
// 小球从左到右运动
     if (ballDirection == 0)
     {
        ballX++;

// 小球从右到左运动

        if (ballX > 100)
        {
          ballDirection = 1;
          ballX = 100;
        }
     }
     else
     {
        ballX--;
        if (ballX <= 0)
        {
          ballDirection = 0;
          ballX = 0;
        }
     }

     repaint();
   }
   //画屏函数
   public void paint(Graphics g)
   {
     g.setXORMode(getBackground());
     g.fillRect(40, 10, 40, 40);
     g.fillOval(ballX, 0, 30, 30);
   }
}
