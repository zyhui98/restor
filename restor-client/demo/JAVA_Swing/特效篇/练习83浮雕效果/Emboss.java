//Emboss.java
import java.awt.*;
import java.applet.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.awt.image.*;
import java.net.URL;

public class Emboss extends Applet
{

    public void paint(Graphics g)
    {
        //实例化Graphics2D 类
        Graphics2D newG = (Graphics2D) g;
        //加载一幅图片并且显示 
        URL imgURL = null;
        try {
            imgURL = new URL(getDocumentBase(), "sunflower.jpg");
        } catch (Exception ignore) {
        }

        Image img = getImage(imgURL);

        MediaTracker tracker = new MediaTracker(this);
        try {
            tracker.addImage(img, 0);
            tracker.waitForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //得到图片的尺寸
        int width = img.getWidth(this);
        int height = img.getHeight(this);
        //创建一个缓冲区进行图片操作
        BufferedImage buff = new BufferedImage(width,
            height, BufferedImage.TYPE_INT_ARGB);
        Graphics tempGr = buff.createGraphics();
        tempGr.drawImage(img, 0, 0, this);
        //创建一个图像缓冲区来存放经过浮雕处理后的图片 
        BufferedImage outBuff = new BufferedImage(width,
            height, BufferedImage.TYPE_INT_ARGB);
        embossImage(buff, outBuff);
        newG.drawImage(outBuff, 100, 100, this);
    }

    //对图片进行浮雕处理
    public void embossImage(BufferedImage srcImage, BufferedImage destImage)
    {

        int width = srcImage.getWidth();
        int height = srcImage.getHeight();

        // 对每一个像素进行循环处理
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {

       //确认左右上下都为0
                int upperLeft = 0;
                int lowerRight = 0;
                //左右
                if ((i > 0) && (j > 0)) {
                   upperLeft = srcImage.getRGB(j-1, i-1) 
                        & 0xffffff;
                }
                //上下
                if ((i < height-1) && (j < width-1)) {
                    lowerRight = srcImage.getRGB(j+1, i+1) 
                        & 0xffffff;
                }

                // 得到红色，绿色，蓝色之间的差别
                int redDiff = ((lowerRight >> 16) & 255) -
                    ((upperLeft >> 16) & 255);
                int greenDiff = ((lowerRight >> 8) & 255) -
                    ((upperLeft >> 8) & 255);
                int blueDiff = (lowerRight & 255) -
                    (upperLeft & 255);

                // 描绘出哪一种颜色的变化最大
                int diff = redDiff;
                if (Math.abs(greenDiff) > Math.abs(diff)) 
                    diff=greenDiff;
                if (Math.abs(blueDiff) > Math.abs(diff)) 
                    diff=blueDiff;

		// 将变化最大的颜色置为灰色
                int greyColor = 128 + diff;

                // 如果灰色过深或者过浅，改变到0-255之间的范围
                if (greyColor > 255) greyColor = 255;
                if (greyColor < 0) greyColor = 0;
		
		//创建一种新的颜色
                int newColor = 0xff000000 + (greyColor << 16) +
                    (greyColor << 8) + greyColor;

                destImage.setRGB(j, i, newColor);
            }
        }
    }
}
