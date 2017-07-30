//Cycler.java
import java.awt.*;
import java.awt.image.*;
import java.applet.*;

//程序的Cycler类
public class Cycler extends Applet implements Runnable { 
  //变量声明
  protected Image origImage; // the image before color cycling
  protected Image cycledImage;   // image after cycling
  protected CycleFilter colorFilter;   // performs the cycling

  protected Thread cycleThread;
  protected int delay = 10;   // milliseconds between cycles

  protected int imageWidth = 200;
  protected int imageHeight = 200;
  
  protected boolean stopStatus = false; //thead should not stop, until true

  //初始化函数
  public void init() { 

    // Create space for the memory image
    byte pixels[] = new byte[imageWidth * imageHeight];

    // We're going to cycle through 16 colors, but leave position 0 alone in
    // the index color model we create, so allow room for 17 slots
    byte red[] = new byte[17];
    byte green[] = new byte[17];
    byte blue[] = new byte[17];

    // Fill slots 1-16 with varying shades of gray (when the red, green,
    // blue values are all equal you get shades of gray ranging from
    // black when all values are 0, to white when all values are 255).

    for (int i=0; i < 16; i++) {
      red[i+1] = (byte)(255);
      green[i+1] = (byte)(i * 16 );
      blue[i+1] = (byte)(255);
    }

    //创建颜色模式    
    IndexColorModel colorModel = new IndexColorModel(8, 17,
                                                     red, green, blue);

    // Now create the image, just go from top to bottom, left to right
    // filling in the colors from 1-16 and repeating.

    for (int i=0; i < imageHeight; i++) {
      for (int j=0; j < imageWidth; j++) {
        pixels[i*imageWidth + j] =
                                  (byte) ((j % 16)+1);
      }
    }

    // Create the uncycled image
    origImage = createImage(new MemoryImageSource(imageWidth,
                                                  imageHeight,
                                                  colorModel, pixels, 0, imageWidth));

    // Create the filter for cycling the colors
    colorFilter = new CycleFilter(1, 16);

    // Create the first cycled image
    cycledImage = createImage(new FilteredImageSource(
                                                      origImage.getSource(),
                                                      colorFilter));
  }

  //画循环的图像
  public synchronized void paint(Graphics g) { 
    g.drawImage(cycledImage, 0, 0, this);
  }

  // 更新图像
  public void update(Graphics g) { 
    paint(g);
  }

  // Cycles the colors and creates a new cycled image. Uses media
  // tracker to ensure that the new image has been created before
  // trying to display. Otherwise, we can get bad flicker.

  public synchronized void doCycle() { 
    // Cycle the colors
    colorFilter.cycleColors();

    // Flush clears out a loaded image without having to create a
    // while new one. When we use waitForID on this image now, it
    // will be regenerated.

    cycledImage.flush();

    MediaTracker myTracker = new MediaTracker(this);
    myTracker.addImage(cycledImage, 0);
    try {

      // Cause the cycledImage to be regenerated
      if (!myTracker.waitForID(0, 1000)) { 
        return;
      }
    } catch (Exception ignore) {
    }
    // Now that we have reloaded the cycled image, ask that it
    // be redrawn.
    repaint();
  }

  // 启动小程序
  public void start() { 
    stopStatus = false; //don't stop yet
    cycleThread = new Thread(this);
    cycleThread.start();
  }
  // 停止小程序
  public void stop() { 
    stopStatus = true;
  }
  // 运行小程序
  public void run() { 
    // Continually cycle colors and wait.
    while (!stopStatus) { 
      doCycle();
      try {
        Thread.sleep(delay);
      } catch (Exception hell) {
      }
    }
  }
}
