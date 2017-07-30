//CursorChoiceTest.java
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class CursorChoiceTest extends Applet {
	//实例化CursorChoice
	private CursorChoice cursorChoice = new CursorChoice();	
	//程序初始化
    public void init() {
		add(cursorChoice);
		//事件监听
		cursorChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				setCursor(cursorChoice.getSelectedCursor());
			}
		});
    }
}
