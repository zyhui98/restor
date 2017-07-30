//ProgressMonitorExample.java
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ProgressMonitorExample extends JPanel {  
  //变量声明
  ProgressThread progressThread;    
  static JFrame myFrame;
  //构造函数
  public ProgressMonitorExample() {    
    setLayout(new BorderLayout());	  
    JPanel buttonPanel = new JPanel();
    JButton startButton = new JButton("Start");
    buttonPanel.add(startButton);
    startButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {    
      startRunning();    
      }
      });  

    JButton stopButton = new JButton("Stop");
    buttonPanel.add(stopButton);
    stopButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {      
      stopRunning();    
      }
      });          
    add(buttonPanel, BorderLayout.SOUTH);  
  } 
  //按下开始按钮的动作
  public void startRunning() {
    if(progressThread == null|| !progressThread.isAlive()) {    
      progressThread = new ProgressThread(this);
      progressThread.start();    
    }  
  }
  //按下停止按钮的动作
  public void stopRunning() {       
    progressThread.setStop(true);          
  }  
 //主函数，程序入口处
  public static void main(String args[]){
    myFrame = new JFrame("进程条");
    ProgressMonitorExample progressMonitorExample = new ProgressMonitorExample();
    myFrame.getContentPane().add("Center",progressMonitorExample);
    myFrame.setSize(200,100);    
    myFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
      System.exit(0);
      }
      });
    myFrame.setVisible(true);
  }
}

//进程类
class ProgressThread extends Thread {
  ProgressMonitor monitor;
  boolean stopStatus = false;
  int min = 0;    
  int max = 50;
  //构造函数
  public ProgressThread(Component parent){
    monitor = new ProgressMonitor(parent,"Progress of Thread","Not Started",min,max);
  }
  //停止
  public void setStop(boolean value){
    stopStatus = value;
  }
  //运行
  public void run () {
    monitor.setNote("Started");
    for (int x=min;x<=max;x++) {
      if(stopStatus){
        monitor.close();
        break;
      }else{
        monitor.setProgress(x);
        monitor.setNote(""+(x*2)+"%");
        try {        
          sleep(100);
        } catch (InterruptedException e) { 
          // Ignore Exceptions        
        }   
      }     
    }    
  }
} 
