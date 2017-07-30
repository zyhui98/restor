//FontApplet2.java
import java.awt.*;
import java.applet.*;
public class FontApplet2 extends Applet
{
	//变量声明
    TextField textField;
    
    //初始化函数
    public void init()
    {
        textField = new TextField(10);
        add(textField);
        textField.setText("32");
    }
    
    //画屏函数
    public void paint(Graphics g)
    {
    	//设置显示文字的高度
        String s = textField.getText();
        int height = Integer.parseInt(s);        
        //设置显示文字的字体
        Font font = new Font("TimesRoman", Font.PLAIN, height);
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        height = fontMetrics.getHeight();
        //设置显示文字的起始行
        int row = 80;
        //逐行显示文字
        g.drawString("This is the first line.", 70, row);
        row += height;
        g.drawString("This is the second line.", 70, row);
        row += height;
        g.drawString("This is the third line.", 70, row);
        row += height;
        g.drawString("This is the fourth line.", 70, row);
    }
    
    //响应用户操作函数
    public boolean action(Event event, Object arg)
    {
        repaint();
        return true;
    }
}
