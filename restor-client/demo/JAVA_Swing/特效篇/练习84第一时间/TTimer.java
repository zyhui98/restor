//TTimer.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.*;

public class TTimer extends JApplet {
    Container container = null;
    Timer timer = null;
    JLabel label = null;
    JSlider slider1 = null;
    JSlider slider2 = null;
    Color[] color = {Color.blue, Color.green, Color.red,
                     Color.yellow, Color.lightGray};

    public void init() {
        //得到小程序的容器面板句柄
        container = this.getContentPane();

        //创建静态标签
        label = new JLabel("第一时间！", JLabel.CENTER);
        label.setBackground(Color.black);
        label.setFont(new Font("Dialog", Font.BOLD, 40));
        label.setOpaque(true);
        container.add(label);

        //创建水平箱子      
        Box box = Box.createHorizontalBox();
        container.add(box, BorderLayout.SOUTH);
        
        //创建竖直箱子 
        Box vbox1 = Box.createVerticalBox();
        box.add(vbox1);

        //创建标签和滑杆
        JLabel initDelay = new JLabel("程序启动时间：快----〉慢", JLabel.CENTER);
        initDelay.setPreferredSize(new Dimension(200, 25));
        vbox1.add(initDelay);
        slider1 = new JSlider(JSlider.HORIZONTAL, 0, 60000, 0);
        slider1.addChangeListener(new SliderListener());
        vbox1.add(slider1);
        JLabel delay = new JLabel("颜色变化时间：快----〉慢", JLabel.CENTER);
        delay.setPreferredSize(new Dimension(200, 25));
        vbox1.add(delay);
        slider2 = new JSlider(JSlider.HORIZONTAL, 0, 2000, 1000);
        slider2.addChangeListener(new SliderListener());
        vbox1.add(slider2);

        Box vbox2 = Box.createVerticalBox();
        box.add(vbox2);

        //创建另一个面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2,5,5));
        vbox2.add(panel);
        //创建按钮
        String[] buttonLabels = {"开始", "停止", "重新启动"};
        for (int i=0; i<buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.addActionListener(new ButtonListener());
            panel.add(button);
        }

        //创建时间控件
        timer = new Timer(slider2.getValue(), new TimerListener());
        timer.setInitialDelay(slider1.getValue()); 
    }

        //监听消息
    class TimerListener implements ActionListener {
        int i;

        public void actionPerformed(ActionEvent e) {
            if (i == color.length) {
                i = 0;
                label.setForeground(color[i]);
            }
            else {
                label.setForeground(color[i]);
            }
            label.repaint();
            i++;
        }
    }

    //监听按钮消息
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (button.getText() == "开始") {
                timer.start();
            }
            else if (button.getText() == "停止") {
                timer.stop();
            }
            else if (button.getText() == "重新启动") {
                timer.restart();
            }
        }
    }

    //监听滑杆消息
    class SliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();

            if (slider == slider1) {
                timer.setInitialDelay(slider1.getValue());
            }
            else if (slider == slider2) {
                timer.setDelay(slider2.getValue());
            }
        }
    }
}