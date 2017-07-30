//ExitableJFrame.java
import javax.swing.*;

public class ExitableJFrame extends JFrame{
        //构造函数
	public ExitableJFrame(){
	}
	//带窗口标题的构造函数
	public ExitableJFrame(String title){
		super(title);
	}
	//窗口的初始化
	protected void frameInit(){
		super.frameInit();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	}