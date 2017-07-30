//DialogTest.java
import java.awt.*;

//testButton类
class testButton extends Canvas {
  int state = testDialog.BLANK;
  testDialog parent;
  testButton(testDialog parent) {
    this.parent = parent;
  }
  //画屏函数
  public void paint(Graphics  g) {
    int x1 = 0;
    int y1 = 0;
    int x2 = size().width - 1;
    int y2 = size().height - 1;
    g.drawRect(x1, y1, x2, y2);
    x1 = x2/4;
    y1 = y2/4;
    int wide = x2/2;
    int high = y2/2;
    if(state == testDialog.XX) {
      g.drawLine(x1, y1, x1 + wide, y1 + high);
      g.drawLine(x1, y1 + high, x1 + wide, y1);
    }
    if(state == testDialog.OO) {
      g.drawOval(x1, y1, x1+wide/2, y1+high/2);
    }
  }
  //响应鼠标按下
  public boolean 
  mouseDown(Event evt, int x, int y) {
    if(state == testDialog.BLANK) {
      state = parent.turn;
      parent.turn= (parent.turn == testDialog.XX ?
        testDialog.OO : testDialog.XX);
    } 
    else
      state = (state == testDialog.XX ? 
        testDialog.OO : testDialog.XX);
    repaint();
    return true;
  }
}

//testDialog类
class testDialog extends Dialog {
  static final int BLANK = 0;
  static final int XX = 1;
  static final int OO = 2;
  int turn = XX;
  public testDialog(Frame parent, int w, int h) {
    super(parent, "The game itself", false);
    setLayout(new GridLayout(w, h));
    for(int i = 0; i < w * h; i++)
      add(new testButton(this));
    resize(w * 50, h * 50);
  }
  public boolean handleEvent(Event evt) {
    if(evt.id == Event.WINDOW_DESTROY) 
      dispose();
    else 
      return super.handleEvent(evt);
    return true;
  }
}

//DialogTest类
public class DialogTest extends Frame {
  TextField rows = new TextField("3");
  TextField cols = new TextField("3");
  public DialogTest() {
    setTitle("Dialog Test");
    Panel p = new Panel();
    p.setLayout(new GridLayout(2,2));
    p.add(new Label("Rows", Label.CENTER));
    p.add(rows);
    p.add(new Label("Columns", Label.CENTER));
    p.add(cols);
    add("North", p);
    add("South", new Button("go"));
  }
  public boolean handleEvent(Event evt) {
    if(evt.id == Event.WINDOW_DESTROY) 
      System.exit(0);
    else 
      return super.handleEvent(evt);
    return true;
  }
  public boolean action(Event evt, Object arg) {
    if(arg.equals("go")) {
      Dialog d = new testDialog(
        this, 
        Integer.parseInt(rows.getText()),
        Integer.parseInt(cols.getText()));
      d.show();
    } 
    else 
      return super.action(evt, arg);
    return true;
  }
  public static void main(String[] args) {
    Frame f = new DialogTest();
    f.resize(200,100);
    f.show();
  }
} 