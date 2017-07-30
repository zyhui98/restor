//GraphicsTest.java 
import java.awt.*;
import java.applet.Applet;

public class GraphicsTest extends Applet 
{
    //实例化GraphicsCards类
    GraphicsCards cards;
    //初始化小程序
    public void init() 
    {
       setLayout(new BorderLayout());
       add("Center", cards = new GraphicsCards());
       Panel p = new Panel();
       p.add(new Button("下一幅"));
       p.add(new Button("上一幅"));
       p.add(new Label("我要看：", Label.RIGHT));

       Choice c;

       p.add(c = new Choice());
       c.addItem("Arc");
       c.addItem("Oval");
       c.addItem("Polygon");
       c.addItem("Rect");
       c.addItem("RoundRect");
       add("North", p);

       resize(400, 400);
    } 
    //响应动作
    public boolean action(Event evt, Object arg) 
    {
       if (evt.target instanceof Choice) 
       {
          ( (CardLayout) cards.getLayout() ).show(cards,(String) arg );
       }
       else 
       {
          if ("下一幅".equals(arg)) 
          {
             ( (CardLayout) cards.getLayout() ).next(cards);
          } 
          else if ("上一幅".equals(arg)) 
          {
             ( (CardLayout) cards.getLayout() ).previous(cards);
          }
       }

       return true;
    }  
}
//GraphicsCards类
class GraphicsCards extends Panel 
{
    public GraphicsCards() 
    {
       setLayout(new CardLayout());
       add("Arc", new ArcCard());
       add("Oval", new ShapeTest( new OvalShape() ) );
       add("Polygon", new ShapeTest( new PolygonShape() ) );
       add("Rect", new ShapeTest( new RectShape() ) );
       add("RoundRect", new ShapeTest( new RoundRectShape() ) );
    }
}
//ArcCard类
class ArcCard extends Panel 
{
    public ArcCard() 
    {
       setLayout(new GridLayout(0, 2));
       add(new ArcPanel(true));
       add(new ArcPanel(false));
       add(new ArcDegreePanel(true));
       add(new ArcDegreePanel(false));
    }
} 

//ArcDegreePanel类
class ArcDegreePanel extends Panel 
{
    boolean filled;

    public ArcDegreePanel(boolean filled) 
    {
       this.filled = filled;
    }

    void arcSteps(Graphics g, 
                  int step, 
                  int x, 
                  int y, 
                  int w, 
                  int h, 
                  Color c1,  
                  Color c2) 
    {
       int a1 = 0;
       int a2 = step;
       int progress = 0;
       g.setColor(c1);
       for (; (a1+a2) <= 360; a1 = a1+a2, a2 += 1 ) 
       {
          if (g.getColor() == c1) 
          {
             g.setColor(c2);
          } 
          else 
          {
             g.setColor(c1);
          }
       
          if (filled) 
          {
             g.fillArc(x, y, w, h, a1, a2);
          } 
          else 
          {
             g.drawArc(x, y, w, h, a1, a2);
          }
       
          progress = a1+a2;
       }  

       if (progress != 360) 
       {
          if (filled) 
          {
             g.fillArc(x, y, w, h, a1, 360 - progress);
          } 
          else 
          {
             g.drawArc(x, y, w, h, a1, 360 - progress);
          }
       }  
    } 
    //画屏函数
    public void paint(Graphics g)
    {
       Rectangle r = bounds();

       arcSteps(g, 3, 0, 0, r.width, r.height, Color.orange, Color.blue);

       arcSteps(g, 
                2, 
                r.width / 4, 
                r.height / 4, 
                r.width / 2, 
                r.height / 2, 
                Color.yellow, 
                Color.green);

       arcSteps(g, 
                1, 
                (r.width  * 3) / 8, 
                (r.height * 3) / 8, 
                r.width / 4, 
                r.height / 4, 
                Color.magenta, 
                Color.white);

    }
}
//ArcPanel类
class ArcPanel extends Panel 
{
    // class variables
    boolean filled;

    public ArcPanel(boolean filled) 
    {
       this.filled = filled;
    }

    public void paint(Graphics g) 
    {
       Rectangle r = bounds();

       g.setColor(Color.yellow);
       if (filled) 
       {
          g.fillArc(0, 0, r.width, r.height, 0, 45);
       } 
       else 
       {
          g.drawArc(0, 0, r.width, r.height, 0, 45);
       }

       g.setColor(Color.green);
       if (filled) 
       {
          g.fillArc(0, 0, r.width, r.height, 90, -45);
       } 
       else 
       {
          g.drawArc(0, 0, r.width, r.height, 90, -45);
       }
    
       g.setColor(Color.orange);
       if (filled) 
       {
          g.fillArc(0, 0, r.width, r.height, 135, -45);
       } 
       else 
       {
          g.drawArc(0, 0, r.width, r.height, 135, -45);
       }

       g.setColor(Color.magenta);

       if (filled) 
       {
          g.fillArc(0, 0, r.width, r.height, -225, 45);
       } 
       else 
       {
          g.drawArc(0, 0, r.width, r.height, -225, 45);
       }

       g.setColor(Color.yellow);
       if (filled) 
       {
          g.fillArc(0, 0, r.width, r.height, 225, -45);
       } 
       else  
       {
          g.drawArc(0, 0, r.width, r.height, 225, -45);
       }

       g.setColor(Color.green);
       if (filled) 
       {
          g.fillArc(0, 0, r.width, r.height, -135, 45);
       } 
       else 
       {
          g.drawArc(0, 0, r.width, r.height, -135, 45);
       }

       g.setColor(Color.orange);
       if (filled) 
       {
          g.fillArc(0, 0, r.width, r.height, -45, -45);
       } 
       else 
       {
          g.drawArc(0, 0, r.width, r.height, -45, -45);
       }

       g.setColor(Color.magenta);
       if (filled) 
       {
          g.fillArc(0, 0, r.width, r.height, 315, 45);
       } 
       else 
       {
          g.drawArc(0, 0, r.width, r.height, 315, 45);
       }

    } 

}
//Shape类
abstract class Shape 
{
    abstract void draw(Graphics g, int x, int y, int w, int h);
    abstract void fill(Graphics g, int x, int y, int w, int h);
}
//RectShape类
class RectShape extends Shape 
{
    void draw(Graphics g, int x, int y, int w, int h) 
    {
       g.drawRect(x, y, w, h);
    }
    
    void fill(Graphics g, int x, int y, int w, int h) 
    {
       g.fillRect(x, y, w, h);
    }
}
//OvalShape类
class OvalShape extends Shape 
{
    void draw(Graphics g, int x, int y, int w, int h) 
    {
       g.drawOval(x, y, w, h);
    }
    
    void fill(Graphics g, int x, int y, int w, int h) 
    {
       g.fillOval(x, y, w, h);
    }
}
//RoundRectShape类
class RoundRectShape extends Shape 
{
    void draw(Graphics g, int x, int y, int w, int h) 
    {
       g.drawRoundRect(x, y, w, h, 10, 10);
    }

    void fill(Graphics g, int x, int y, int w, int h) 
    {
       g.fillRoundRect(x, y, w, h, 10, 10);
    }
}
//PolygonShape类
class PolygonShape extends Shape 
{
    Polygon p;
    Polygon pBase;

    public PolygonShape() 
    {
       pBase = new Polygon();
       pBase.addPoint(0, 0);
       pBase.addPoint(10, 0);
       pBase.addPoint(5, 15);
       pBase.addPoint(10, 20);
       pBase.addPoint(5, 20);
       pBase.addPoint(0, 10);
       pBase.addPoint(0, 0);
    }

    void scalePolygon(float w, float h)
    {
       p = new Polygon();
       for (int i = 0; i < pBase.npoints; ++i)
       {
          p.addPoint( (int) (pBase.xpoints[i] * w), 
                      (int) (pBase.ypoints[i] * h) );
       }

    }

    void draw(Graphics g, int x, int y, int w, int h) 
    {
       Graphics ng = g.create();
       ng.translate(x, y);
       scalePolygon( (float) ( (float) w / (float) 10 ), 
                     (float) ( (float) h / (float) 20 ) );
       ng.drawPolygon(p);
    }

    void fill(Graphics g, int x, int y, int w, int h) 
    {
       Graphics ng = g.create();
       ng.translate(x, y);
       scalePolygon( (float) ( (float) w / (float) 10 ), 
                     (float) ( (float) h / (float) 20 ) );
       ng.fillPolygon(p);
    }
}
//ShapeTest类
class ShapeTest extends Panel 
{
    Shape   shape;
    int     step;

    public ShapeTest(Shape shape, int step) 
    {
       this.shape = shape;
       this.step = step;
    }

    public ShapeTest(Shape shape) 
    {
       this(shape, 10);
    }

    public void paint(Graphics g) 
    {
       Rectangle bounds = bounds();

       int cx, cy, cw, ch;

       Color color;
   
       for (color=Color.red, 
            cx=bounds.x, 
            cy=bounds.y, 
            cw=bounds.width / 2, 
            ch=bounds.height ; 

            cw > 0 && ch > 0 ;

            cx+=step, 
            cy += step, 
            cw -= (step * 2), 
            ch -= (step * 2),
            color=ColorUtils.darker(color, 0.9) ) 
       {
          g.setColor(color);
          shape.draw(g, cx, cy, cw, ch);
       }

       for ( cx=bounds.x + bounds.width / 2, 
             cy=bounds.y,
             cw=bounds.width / 2, ch=bounds.height ; 

             cw > 0 && ch > 0;
          
             cx+=step, 
             cy += step, 
             cw -= (step * 2),
             ch -= (step * 2) ) 
       {
          if (g.getColor() == Color.red) 
          {
             g.setColor(Color.blue);
          } 
          else 
          {
             g.setColor(Color.red);
          }

          shape.fill(g, cx, cy, cw, ch);
       }
    }
}
//ColorUtils类
class ColorUtils 
{
    static Color brighter(Color c, double factor) 
    {
       return new Color( Math.min((int)(c.getRed()  *(1/factor)), 255), 
                         Math.min((int)(c.getGreen()*(1/factor)), 255),
                         Math.min((int)(c.getBlue() *(1/factor)), 255) );
    }

    static Color darker(Color c, double factor) 
    {
       return new Color( Math.max((int)(c.getRed()  *factor), 0), 
                         Math.max((int)(c.getGreen()*factor), 0),
                         Math.max((int)(c.getBlue() *factor), 0) );
    }
}