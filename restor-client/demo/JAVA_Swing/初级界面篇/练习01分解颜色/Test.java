//Test.java
import javax.swing.*;
import javax.swing.colorchooser.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JApplet {
	//变量定义
	private JColorChooser chooser = new JColorChooser();
	private JButton button = new JButton("Show Color Chooser");
	private JDialog dialog;
 
        //初始化程序
	public void init() {
		//制作界面容器
		Container contentPane = getContentPane();

		contentPane.setLayout(new FlowLayout());
		contentPane.add(button, BorderLayout.CENTER);
                //选择框
		chooser.setPreviewPanel(new PreviewPanel());

                //事件监听
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(dialog == null) 
					dialog	= JColorChooser.createDialog(
							    Test.this,  // parent comp
								"Pick A Color",   // dialog title
								false, 			  // modality
								chooser,		  
								null, null);

				dialog.setVisible(true);
			}
		});
	}
	//制作预览面板
	class PreviewPanel extends JPanel {
		public PreviewPanel() {
			setPreferredSize(new Dimension(0,100));
			setBorder(BorderFactory.createRaisedBevelBorder());
		}
		public void paintComponent(Graphics g) {
			Dimension size = getSize();

			g.setColor(getForeground());
			g.fillRect(0,0,size.width,size.height);
		}
	}
}
