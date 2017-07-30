//StarField.java
import java.awt.*;
//star类
class Star {
	int	H, V;
	int	x, y, z;
	int	type;
        //构造函数
	Star( int width, int height, int depth, int type )
		{
		this.type = type;
		H = width/2;
		V = height/2;
		x = (int)(Math.random()*width) - H;
		y = (int)(Math.random()*height) - V;
		if( (x == 0) && (y == 0 ) ) x = 10;
		z = (int)(Math.random()*depth);
		}
        //画星星函数
	public void Draw( Graphics g, double rot )
		{
		double	X, Y;
		int	h, v, hh, vv;
		int	d;
		z-=2;
		if( z < -63 ) z = 100;
		hh = (x*64)/(64+z);
		vv = (y*64)/(64+z);
		X = (hh*Math.cos(rot))-(vv*Math.sin(rot));
		Y = (hh*Math.sin(rot))+(vv*Math.cos(rot));
		h = (int)X+H;
		v = (int)Y+V;
		if( (h < 0) || (h > (2*H))) z = 100;
		if( (v < 0) || (v > (2*H))) z = 100;
		GrayMe(g);
		if( type == 0 )
			{
			d=(100-z)/50;
			if( d == 0 ) d = 1;
			g.fillRect( h, v, d, d );
			}
		else
			{
			d=(100-z)/20;
			g.drawLine( h-d, v, h+d, v );
			g.drawLine( h, v-d, h, v+d );
			if( z < 50 ) {
				d/=2;
				g.drawLine( h-d, v-d, h+d, v+d );
				g.drawLine( h+d, v-d, h-d, v+d );
				}
			}
		}
	//设置颜色函数
	public void GrayMe(Graphics g)
		{
		if ( z > 50 )
			{
			g.setColor( Color.gray );
			}
		else if ( z > 25 )
			{
			g.setColor( Color.lightGray );
			}
		else
			{
			g.setColor( Color.white );
			}
		}
	}
//StarField类
public class StarField extends java.applet.Applet implements Runnable
	{
	//变量定义
	int		Width, Height;
	Thread		me = null;
	boolean		suspend = false;
	Image		im;
	Graphics	offscreen;
	double		rot, dx, ddx;

	int		speed, stars, type;
	double		defddx, max;
	Star		pol[];	
	//初始化函数
	public void init()
		{
		rot = 0;
		dx=0;
		ddx=0;
		Width = size().width;
		Height = size().height;

		String	theSpeed = getParameter( "speed" );
		Show( "speed", theSpeed );
		speed = (theSpeed == null ) ? 50 : Integer.valueOf( theSpeed ).intValue();

		String	theStars = getParameter( "stars" );
		Show( "stars", theStars );
		stars = (theStars == null ) ? 30 : Integer.valueOf( theStars ).intValue();

		String theType = getParameter( "type" );
		Show( "type", theType );
		type = (theType == null ) ? 0 : Integer.valueOf( theType ).intValue();

		String theRot = getParameter( "spin" );
		Show( "spin", theRot );
		rot = (theRot == null) ? 0 : Double.valueOf( theRot ).doubleValue();

		String theMax = getParameter( "maxspin" );
		Show( "maxspin", theRot );
		max = (theMax == null) ? .1 : Double.valueOf( theMax ).doubleValue();

		String theddx = getParameter( "ddx" );
		Show( "ddx", theddx );
		defddx = (theddx == null) ? .005 : Double.valueOf( theddx ).doubleValue();

		try
			{
			im = createImage( Width, Height );
			offscreen = im.getGraphics();
			}
		catch( Exception e)
			{
			offscreen = null;
			}
		pol = new Star[stars];
		for( int i = 0; i < stars; i++ )
			pol[i] = new Star( Width, Height, 100, type );
		}
	//画屏函数
	public void paint( Graphics g )
		{
		if( offscreen != null )
			{
			paintMe( offscreen );
			g.drawImage( im, 0, 0, this );
			}
		else
			{
			paintMe( g );
			}
		}

        //着色
	public void paintMe( Graphics g )
		{
		g.setColor( Color.black );
		g.fillRect( 0, 0, Width, Height );
		
		for( int i = 0; i < stars; i++ )
			pol[i].Draw( g, rot );
		}
	//启动线程函数
	public void start()
		{
		if( me == null )
			{
			me = new Thread( this );
			me.start();
			}
		}
	//停止线程
	public void stop()
		{
		if( me != null )
			{
			me.stop();
			me = null;
			}
		}
	//运行
	public void run()
		{
		while( me != null )
			{
			rot += dx;
			dx += ddx;
			if( dx > max ) ddx=-defddx;
			if( dx < -max) ddx=defddx;
			try { Thread.sleep( speed ); }
			catch (InterruptedException e){}
			repaint();
			}
		}
	//更新屏幕
	public void update( Graphics g )
		{
		paint( g );
		}
	//响应按下鼠标
	public boolean mouseDown( java.awt.Event evt, int x, int y )
		{
		ddx = (ddx == 0) ? defddx : 0;
		return true;
		}

	public void Toggle( )
		{
		if( me != null )
			{
			if( suspend )
				{
				me.resume();
				}
			else
				{
				me.suspend();
				}
			suspend = !suspend;
			}
		}
	//显示函数
	public void Show( String theString, String theValue )
		{
		if( theValue == null )
			{
			System.out.println( theString + " : null");
			}
		else
			{
			System.out.println( theString + " : " + theValue );
			}
		}
	}
