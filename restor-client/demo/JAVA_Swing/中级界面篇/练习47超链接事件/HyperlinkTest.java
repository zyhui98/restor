//HyperlinkTest.java
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class HyperlinkTest {
	//主函数
	public  static void main(String args[]){
		//变量定义
		JFrame eframe = new ExitableJFrame("Hyperlink Listener");
		//实例化容器
		Container contentPane = eframe.getContentPane();
		//实例化编辑面板
		final JEditorPane ep = new JEditorPane();
		//设定目标网页
		try {
			ep.setPage("http://www.sohu.com");
		}catch(IOException e){
			System.err.println("Bad URL:"+e);
			System.exit(-1);
		}
		//设定超链接事件监听
		HyperlinkListener listener = new HyperlinkListener(){
		public void hyperlinkUpdate(HyperlinkEvent e){
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
				try{
				ep.setPage(e.getURL());
			}catch(IOException ioe){
				System.err.println("Error loading:"+ioe);
			}
			}
		  }
	        };
	//添加事件监听        
 	ep.addHyperlinkListener(listener);
 	//设定不可编辑
 	ep.setEditable(false);
 	//实例化滚动面板
 	JScrollPane pane = new JScrollPane(ep);
 	contentPane.add(pane,BorderLayout.CENTER);
 	//设定窗口尺寸
 	eframe.setSize(640,480);
 	eframe.show();
}
}
			
