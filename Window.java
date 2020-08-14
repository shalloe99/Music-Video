import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Window extends Frame implements WindowListener{
  int length,width;
  JFrame frame;
  /**
  setWindowSize// Set Window size
  @param Integer length
  @param Integer width
  */
  public Window(int length,int width){
    //JFrame setup
    frame = new JFrame();
    this.length = length;
    this.width = width;
    frame.setSize(length,width);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addWindowListener(this);
  }
  /**
  addPanel// Put JPanel on the JFrame
  @param JPanel JPanel
  */
  public void addPanel(JPanel panel){
    //Display JPanel to JFrame;
    frame.add(panel);
  }
  /* WindowEvent handlers */
   // Called back upon clicking close-window button
   public void windowClosing(WindowEvent evt) {
     System.out.println("Window Closed");
     System.exit(0);  // Terminate the program
   }
   // Not Used, BUT need to provide an empty body to compile.
@Override public void windowOpened(WindowEvent evt) { /*System.out.println("Window Opened");*/}
@Override public void windowClosed(WindowEvent evt) { /*System.out.println("Window Closed");*/}
   // For Debugging
@Override public void windowIconified(WindowEvent evt) {/* System.out.println("Window Iconified");*/ }
@Override public void windowDeiconified(WindowEvent evt) {/* System.out.println("Window Deiconified");*/ }
@Override public void windowActivated(WindowEvent evt) { /*System.out.println("Window Activated"); */}
@Override public void windowDeactivated(WindowEvent evt) { /*System.out.println("Window Deactivated");*/ }

}// Window class ends
