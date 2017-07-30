//TokenApplet.java
import java.awt.*;
import java.applet.*;
import java.util.StringTokenizer;
public class TokenApplet extends Applet
{
	//变量定义
    TextField textField1;
    Button button1;
    //初始化函数
    public void init()
    {
        textField1 = new TextField(30);
        add(textField1);
        button1 = new Button("Tokenize");
        add(button1);
        resize(300, 300);
    }
    //刷屏函数
    public void paint(Graphics g)
    {
        String str = textField1.getText();
        StringTokenizer tokenizer =
            new StringTokenizer(str);
        int row = 110;
        while (tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();
            g.drawString(token, 80, row);
            row += 20;
        }
    }
    //响应用户操作
    public boolean action(Event evt, Object arg)
    {
        if (arg == "Tokenize")
        {
            repaint();
            return true;
        }
        else
            return false;
    }
}
