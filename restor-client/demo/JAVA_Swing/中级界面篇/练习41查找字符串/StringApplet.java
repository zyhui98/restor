//StringApplet.java
import java.awt.*;
import java.applet.*;
public class StringApplet extends Applet
{
    TextField textField1;
    TextField textField2;
    Button button1;
    String displayStr;
    public void init()
    {
        Label label = new Label("原字符串");
        add(label);
        textField1 = new TextField(20);
        add(textField1);
        label = new Label("要查找的字符串");
        add(label);
        textField2 = new TextField(20);
        add(textField2);
        button1 = new Button("查找");
        add(button1);
        displayStr = "";
        resize(230, 400);
    }
    public void paint(Graphics g)
    {
        g.drawString(displayStr, 80, 150);
    }
    public boolean action(Event evt, Object arg)
    {
        if (arg == "查找")
        {
            String str = textField1.getText();
            String substr = textField2.getText();
            int index = str.indexOf(substr) + 1;
            displayStr = "位置： " + str.valueOf(index);
            repaint();
            return true;
        }
        else
            return false;
    }
}
