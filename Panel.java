import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Panel extends JPanel implements MouseListener{
  JPanel panel;
  JButton button;
  Image imageClass;
  int imageWidth,imageHeight;
  Image image;
  Lyrics lyrics;
  Scheduler scheduler;
  Buttons buttons;
  AudioSong audio;
  String state;
  /**
  Panel Constructor// Set up Panel class Constructor
  @param Nothing
  */
  public Panel() throws IOException{
    // Read Images
    imageClass = new Image();
    image = new Image();
    image.readImage("images.txt");
    lyrics = new Lyrics();
    lyrics.readLyrics("lyrics.txt");
    buttons = new Buttons();
    buttons.readButtons("buttons.txt");
    audio = new AudioSong();
    scheduler = new Scheduler();
    scheduler.readSchedule("schedule.txt");
    scheduler.readSong("song.txt");
    state = "Menu";

    addMouseListener(this);
  }// Constructor extends
  // Mouse Listeners handlers
  @Override
  public void mousePressed(MouseEvent e)  {
  }
  @Override
  public void mouseReleased(MouseEvent e) {
  }
  @Override
  public void mouseEntered(MouseEvent e)  {
  }
  @Override
  public void mouseExited(MouseEvent e)   {
  }
  @Override
  public void mouseClicked(MouseEvent e)  {
      int x=e.getX();
      int y=e.getY();
      if(state.equals("Menu")){
        if(isIn(x,y,390,360,405,55)){
          System.out.println("Start clicked");
          state = "Run";
          audio.start();
          scheduler.start(6,1);//(delay,speed)
        }else if(isIn(x,y,390,430,405,55)){
          System.out.println("www.google.com-----Search by yourself.");
          System.out.println("Web clicked");
        }else if(isIn(x,y,390,500,405,55)){
          System.out.println("Shawn Kang, Royce Weigel, Neel Pochareddy, Mateen Saghafi");
          System.out.println("Team clicked");
        }else if(isIn(x,y,390,570,405,55)){
    //      System.out.println("Exit clicked");
          System.exit(0);
        }
      }
      else if(state.equals("Pause")){
        if(isIn(x,y,100,130,430,60)){
          System.out.println("Continue clicked");
          state = "Run";
          audio.resume();
          scheduler.resume(0,1);
        }else if(isIn(x,y,80,220,420,55)){
          System.out.println("Back clicked");
          state = "Menu";
          scheduler.reset();
          audio.reset();
        }else if(isIn(x,y,60,310,420,55)){
      //    System.out.println("Exit clicked");
          System.exit(0);
          }
        }
      else if(state.equals("End")){
        if(isIn(x,y,390,360,400,55)){
          System.out.println("Back clicked");
          state = "Menu";
          scheduler.reset();
          audio.reset();
        }
        else if(isIn(x,y,390,470,400,55)){
          System.exit(0);
          }
        }
      else if(state.equals("Run")){
        if(isIn(x,y,0,0,1200,800)){
          state = "Pause";
          scheduler.pause();
          audio.stop();
        }
      }
  }
  /**
  setWindowSize// Set Window size
  @param Integer current X coordinate
  @param Integer current Y coordinate
  @param Integer X coordinate
  @param Integer Y coordinate
  @param Integer width
  @param Integer height
  */
  public Boolean isIn(int your_x,int your_y,int posx,int posy,int width,int height){
    if (your_x>=posx && your_x<=(posx+width) && your_y>=posy && your_y<=(posy+height)){
      return true;
    }
    else{
      return false;
    }
  }
  /**
  Paint Class// Paint controls channel; Paint the images and lyrics
  @param Graphics Graphics
  */
  public void paint(Graphics g) {
    super.paintComponent(g);
    if (state.equals("Menu")){
      displayMenu(g);
      //Test Rectangle
      /*g.setColor(Color.red);
      g.drawRect(390,360,405,55);
      g.drawRect(390,430,405,55);
      g.drawRect(390,500,405,55);
      g.drawRect(390,570,405,55);*/
    }
    else if (state.equals("Pause")){
      int pauseImage = scheduler.imageOrder();
      int pauseLyrics = scheduler.lyricsOrder();
      displayPauseScreen(g,pauseImage,pauseLyrics);
        // Rect Test
      /*  g.setColor(Color.red);
        g.drawRect(100,130,430,60);
        g.drawRect(80,220,420,55);
        g.drawRect(60,310,420,55);*/
    }
    else if (state.equals("End")){
      displayEnd(g);
      // Rect Test
    /*  g.setColor(Color.red);
      g.drawRect(390,360,420,55);
      g.drawRect(390,470,420,55);*/
    }
    else if (state.equals("Run")){
      int currentTime = scheduler.timeNow();
      if (currentTime == 262){
        state = "End";
      }else{
        int currentImage = scheduler.imageOrder();
        int currentLyrics = scheduler.lyricsOrder();
      //  int currentSchedule = scheduler.scheduleOrder();
        displayImage(g,currentImage);
        displayLyrics(g,currentLyrics);
        //System.out.println("currentTime "+ currentTime);
      }
    }
    repaint();
  }
  private void displayImage(Graphics g,int imagenum){
    BufferedImage presentImage = image.wantImage(imagenum);
    g.drawImage(presentImage,0,0,this);
  }
  private void displayLyrics(Graphics g,int lyricsnum){
    Font font = new Font("Verdana", Font.BOLD, 35);
    String presentLyrics = lyrics.wantLyrics(lyricsnum);
    g.setFont(font);
    g.setColor(Color.white);
    g.drawString(presentLyrics,30,600);
  }
  private void displayMenu(Graphics g){
    BufferedImage presentImage = image.wantImage(0);
    g.drawImage(presentImage,0,0,this);
    Font font = new Font("Verdana", Font.BOLD, 90);
    g.setFont(font);
    g.drawString("FAST CAR", 365, 280);
    g.setColor(Color.white);
    font = new Font("Segoe Script", Font.BOLD, 62);
    g.drawString("FAST CAR", 345, 280);
    BufferedImage button_start = buttons.wantButtons(0);
    BufferedImage button_web = buttons.wantButtons(1);
    BufferedImage button_team = buttons.wantButtons(2);
    BufferedImage button_exit = buttons.wantButtons(3);
    g.drawImage(button_start,390,360,this);
    g.drawImage(button_web,390,430,this);
    g.drawImage(button_team,390,500,this);
    g.drawImage(button_exit,390,570,this);
  }
  private void displayPauseScreen(Graphics g,int imagenum,int lyricsnum){
    BufferedImage presentImage = image.wantImage(imagenum);
    g.drawImage(presentImage,0,0,this);
    Font font = new Font("Verdana", Font.BOLD, 90);
    g.setFont(font);
    g.setColor(Color.white);
    g.drawString("PAUSE", 85, 90);
    g.setColor(Color.black);
    font = new Font("Segoe Script", Font.BOLD, 62);
    g.drawString("PAUSE", 65, 90);
    BufferedImage button_continue = buttons.wantButtons(5);
    BufferedImage button_back = buttons.wantButtons(4);
    BufferedImage button_exit = buttons.wantButtons(3);
    g.drawImage(button_continue,100,130,this);
    g.drawImage(button_back,80,220,this);
    g.drawImage(button_exit,60,310,this);
    font = new Font("Verdana", Font.BOLD, 35);
    String presentLyrics = lyrics.wantLyrics(lyricsnum);
    g.setFont(font);
    g.drawString(presentLyrics,30,600);
  }
  private void displayEnd(Graphics g){
    BufferedImage presentImage = image.wantImage(19);
    g.drawImage(presentImage,0,0,this);
    Font font = new Font("Verdana", Font.BOLD, 90);
    g.setFont(font);
    g.drawString("THANK YOU", 305, 280);
    g.setColor(Color.white);
    font = new Font("Segoe Script", Font.BOLD, 62);
    g.drawString("THANK YOU", 285, 280);
    BufferedImage button_back = buttons.wantButtons(4);
    BufferedImage button_exit = buttons.wantButtons(3);
    g.drawImage(button_back,390,360,this);
    g.drawImage(button_exit,390,470,this);
  }
}// Class ends
