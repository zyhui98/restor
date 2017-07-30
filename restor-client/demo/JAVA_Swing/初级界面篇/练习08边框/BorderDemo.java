//BorderDemo.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

public class BorderDemo extends JFrame 
{
    public BorderDemo() 
    {
        //设置框架窗口的标题
        super("使用边框的例子");
        //定义5个简单类型的边框
        Border blackline, etched, raisedbevel, loweredbevel, empty;
        //创建黑色的线状边框
        blackline = BorderFactory.createLineBorder(Color.black);
        //创建蚀刻边框
        etched = BorderFactory.createEtchedBorder();
        //创建凸出边框
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        //创建凹陷边框
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        //创建空的边框
        empty = BorderFactory.createEmptyBorder();

        //第一个面板：简单边框
        JPanel simpleBorders = new JPanel();
        simpleBorders.setLayout(new BoxLayout(simpleBorders, BoxLayout.Y_AXIS));

        addCompForBorder(blackline, "线状边框",simpleBorders);
        addCompForBorder(etched, "蚀刻边框",simpleBorders);
        addCompForBorder(raisedbevel, "凸出边框",simpleBorders);
        addCompForBorder(loweredbevel, "凹陷边框",simpleBorders);
        addCompForBorder(empty, "空的边框",simpleBorders);

     
        //第二个面板：带有标题的面板
        JPanel titledBorders = new JPanel();
        titledBorders.setLayout(new BoxLayout(titledBorders,BoxLayout.Y_AXIS));
        TitledBorder titled;

        titled = BorderFactory.createTitledBorder("标题");
        addCompForBorder(titled,
                         "带标题的线状边框"
                         + " (标题默认的位置在左端)",
                         titledBorders);

        titled = BorderFactory.createTitledBorder(blackline, "标题");
        addCompForTitledBorder(titled,
                               "带标题的黑色的线状边框"
                                   + " (标题默认的位置在中央)",
                               TitledBorder.CENTER,
                               TitledBorder.DEFAULT_POSITION,
                               titledBorders);

        titled = BorderFactory.createTitledBorder(etched, "标题");
        addCompForTitledBorder(titled,
                               "带标题的蚀刻边框"
                                   + " (标题默认的位置在右端)",
                               TitledBorder.RIGHT,
                               TitledBorder.DEFAULT_POSITION,
                               titledBorders);

        titled = BorderFactory.createTitledBorder(loweredbevel, "标题");
        addCompForTitledBorder(titled,
                               "带标题的凹陷边框"
                                   + " (标题默认的位置在上端)",
                               TitledBorder.DEFAULT_JUSTIFICATION,
                               TitledBorder.ABOVE_TOP,
                               titledBorders);

        titled = BorderFactory.createTitledBorder(empty, "标题");
        addCompForTitledBorder(titled, "带标题的空的边框"
                               + " (标题默认的位置在下端)",
                               TitledBorder.DEFAULT_JUSTIFICATION,
                               TitledBorder.BOTTOM,
                               titledBorders);

  
        //创建TabbledPane组件
        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("简单的边框", null, simpleBorders, null);
       
        tabbedPane.addTab("带有标题的边框", null, titledBorders, null);
 
        tabbedPane.setSelectedIndex(0);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

	//添加带有标题的边框
    void addCompForTitledBorder(TitledBorder border,
                                String description,
                                int justification,
                                int position,
                                Container container) 
    {
        border.setTitleJustification(justification);
        border.setTitlePosition(position);
        addCompForBorder(border, description,
                         container);
    }

	//添加简单的边框
    void addCompForBorder(Border border,
                          String description,
                          Container container) 
    {
        JPanel comp = new JPanel(false);
        JLabel label = new JLabel(description, JLabel.CENTER);
        comp.setLayout(new GridLayout(1, 1));
        comp.add(label);
        comp.setBorder(border);

        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(comp);
    }

	//程序的入口方法
    public static void main(String[] args) 
    {
        JFrame frame = new BorderDemo();
        //添加框架窗口的事件监听（监听关闭框架窗口事件）
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //正常推出Java虚拟机
                System.exit(0);
            }
        });

		//显示框架窗口
        frame.pack();
        frame.setVisible(true);
    }
}