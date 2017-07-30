//CircleApplet.java
import java.applet.*;
import java.awt.*;
//这个类调用了自己写的类CircleCanvas
public class CircleApplet extends Applet
{
     public void init()
     {
     	  //变量定义
          GridBagLayout gridbag = new GridBagLayout();
          GridBagConstraints constraints = new GridBagConstraints();
          CircleCanvas newCircle;
	      //设置界面布局方式
          setLayout(gridbag);
          //设置画圆属性
          constraints.weightx = 1.0;
          constraints.weighty = 1.0;
          constraints.fill = GridBagConstraints.BOTH;
	      //创建一个圆并且加载
          newCircle = new CircleCanvas(Color.red);
          gridbag.setConstraints(newCircle, constraints);
          add(newCircle);
		  //设置大圆属性
          constraints.weightx = 2.0;
          constraints.weighty = 2.0;
          //创建一个蓝色圆圈并且加载
          newCircle = new CircleCanvas(Color.blue);
          gridbag.setConstraints(newCircle, constraints);
          add(newCircle);
          //创建第三个圆，跟第一个一样大
          constraints.weightx = 1.0;
          constraints.weighty = 1.0;
          //创建一个绿色圆圈并且加载       
          newCircle = new CircleCanvas(Color.green);
          gridbag.setConstraints(newCircle, constraints);
          add(newCircle);          
     }
}
