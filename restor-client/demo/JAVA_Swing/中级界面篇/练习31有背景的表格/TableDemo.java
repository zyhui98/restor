//TableDemo.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableDemo
{
	//程序的入口方法
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Table");
		frame.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				//正常退出Java虚拟机
				System.exit(0);
			}
		} );
		
		// 创建带有背景的表格组件
		ImageTable table = new ImageTable( 15, 3 );
		//给表格组件的每个单元设置坐标值
		for( int i=0; i<table.getRowCount(); i++ ){
			for( int j=0; j< table.getColumnCount(); j++ ){
				table.setValueAt("(" + i+ "," +j+ ")",i,j);
			}
		}
		//把表格组件加入滚动面板中
		JScrollPane sp = new JScrollPane( table );
		//将滚动面板加入框架窗口中
		frame.getContentPane().add( sp );
		//显示框架窗口
		frame.pack();
		frame.show();
	}
}
/*
 * 一个定制的表格组件，其背景为图片
*/
class ImageTable extends JTable
{
	// 要设置的表格的背景图片，可以替换成自己喜爱的图片.
	ImageIcon image = new ImageIcon( "clouds.jpg" );
	
	//表格组件的构造方法
	public ImageTable(int rows, int cols)
	{
		super(rows,cols);		
	}
	public ImageTable(Object[][] rowData, Object[] columnNames)
	{
		super(rowData,columnNames);		
	}
	public ImageTable(java.util.Vector rowData, java.util.Vector columnNames)
	{
		super(rowData,columnNames);		
	}
	public ImageTable(TableModel row, TableColumnModel column)
	{
		super(row,column);		
	}
	public ImageTable(TableModel model)
	{
		super(model);		
	}
	//准备绘制器
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) 
	{
		
		Component c = super.prepareRenderer( renderer, row, column);
		//使背景图片可见
		if( c instanceof JComponent )
			((JComponent)c).setOpaque(false);
		return c;
	}
	//绘制表格组件	
	public void paint( Graphics g )
	{
		// 设置表格组件的背景是透明的
		setOpaque(false);
		//获取表格组件的大小
		Dimension d = getSize();
		// 用平铺方式画背景图片
		for( int x = 0; x < d.width; x += image.getIconWidth() )
			for( int y = 0; y < d.height; y += image.getIconHeight() )
				g.drawImage( image.getImage(), x, y, null, null );
		//调用父类的方法
		super.paint(g);
	}
				
}
