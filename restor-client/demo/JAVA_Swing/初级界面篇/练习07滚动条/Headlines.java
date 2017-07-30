//Headlines.java
import java.awt.*;
import javax.swing.*;
import java.util.*;

//Headlines类
public class Headlines extends JFrame{
	HeadlinePanel news =new HeadlinePanel();
	public Headlines(){
		super("Headline");
		setSize(400,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(1,1,15,15));
		pane.add(news);
		setContentPane(pane);
		show();
		news.scroll();
	}
	public static void main(String[] arguments){
		Headlines head = new Headlines();
	}
}

//HeadlinePanel类
class HeadlinePanel extends JPanel{
 	String[] headlines = {
 		"Yesterday",
 		"Beatles, 1965",
 		" ",
 		"Yesterday all my troubles seemed so far away",
 		"Now it looks as though they're here to stay",
 		"Oh I believe in yesterday",
 		
 		"Suddenly, I'm not half the man I used to be",
 		"There's shadow hanging over me",
 		"Oh Yesterday came suddenly",
 		
 		"Why she had to go I don't know",
 		"She wouldn't say",
 		"I said something wrong now I long for yesterday",
 		
 		"Yesterday love was such an easy game to play",
 		"Now I need a place to hideaway",
 		"Ho I believe in yesterday",
 		
 		"Why she had to go I don't know",
 		"She wouldn't say",
 		"I said something wrong now I long for yesterday",
 		
 		"Yesterday love was such an easy game to play",
 		"Now I need a place to hideaway",
 		"Ho I believe in yesterday"
 		
 	};
 	int y = 201;
 	//处理滚动
 	void scroll(){
 		while(true){
 			y = y-1;
 			if(y<-200)
 			y = 201;
 			repaint();
 			try{
 				Thread.sleep(50);
 			}catch(InterruptedException e){}
 		}
 	}
 	//画屏
 	public void paintComponent(Graphics comp){
 		Graphics2D comp2D = (Graphics2D)comp;
 		Font type = new Font("monospaced",Font.BOLD,14);
 		comp2D.setFont(type);
 		comp2D.setColor(getBackground());
 		comp2D.fillRect(0,0,getSize().width,getSize().height);
 		comp2D.setColor(Color.black);
			for(int i = 0;i < headlines.length;i++)
				comp2D.drawString(headlines[i],5,y + (20*i));
	}
}