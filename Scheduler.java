import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.*;

public class Scheduler{
  int time;
  int secondsPassed;
  Timer myTimer;
  TimerTask task;
  ArrayList<Integer> secondSchedule;
  ArrayList<Integer> Song;
  int imageOrder,lyricsOrder,scheduleOrder,songOrder;
  Window window;
  Panel panel;
  Boolean check;
  /**
  Scheduler class Constructor
  @param Nothing
  */
  public Scheduler(){
    // Attributes created
    secondsPassed = 0;
    scheduleOrder = 0;
    imageOrder = 0;
    lyricsOrder = 0;
    songOrder = 0;
    secondSchedule = new ArrayList<Integer>();
    Song = new ArrayList<Integer>();
    // Timer Run
    check = false;
    myTimer = new Timer();
    task = new TimerTask(){
      public void run(){
        secondsPassed++;
        //System.out.println(secondSchedule.get(scheduleOrder));
        if(secondsPassed == secondSchedule.get(scheduleOrder)){
          //System.out.println("Switch "+imageOrder +" "+ scheduleOrder);
          scheduleOrder++;
          imageOrder++;
        }
        if (secondsPassed == Song.get(songOrder)){
          songOrder++;
          lyricsOrder++;
        }
        System.out.println("secondsPassed "+secondsPassed);
      }
    };
  }
  public void start(int delay,int second){
    myTimer.scheduleAtFixedRate(task,delay*1000,second*1000);
  }
  public void pause() {
    myTimer.cancel();
    myTimer.purge();
  }
  public void resume(int delay,int second) {
    myTimer = new Timer();
    task = new TimerTask(){
      public void run(){
        secondsPassed++;
        if(secondsPassed == secondSchedule.get(scheduleOrder)){
          imageOrder++;
          scheduleOrder++;
        }
        if (secondsPassed == Song.get(songOrder)){
          songOrder++;
          lyricsOrder++;
        }
        System.out.println("secondsPassed "+secondsPassed);
      }
    };
    myTimer.scheduleAtFixedRate(task,delay*1000,second*1000);
    //System.out.println("Song continue.");
  }
  public void reset(){
    songOrder = 0;
    lyricsOrder = 0;
    secondsPassed = 0;
    scheduleOrder = 0;
    imageOrder = 0;
    myTimer = new Timer();
    task = new TimerTask(){
      public void run(){
        secondsPassed++;
        if(secondsPassed == secondSchedule.get(scheduleOrder)){
          scheduleOrder++;
          imageOrder++;
        }
        if (secondsPassed == Song.get(songOrder)){
          songOrder++;
          lyricsOrder++;
        }
        System.out.println("secondsPassed "+secondsPassed);
      }
    };
  }
  public int timeNow(){
    return secondsPassed;
  }
  public int scheduleOrder(){
    return scheduleOrder;
  }
  public int imageOrder(){
    return imageOrder;
  }
  public int lyricsOrder(){
    return lyricsOrder;
  }
  public void readSchedule(String file) throws IOException{
    // Read from file to take all Image's names
      String fileName = file;
      try {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = in.readLine()) != null)
        {
        int seconds = Integer.parseInt(line);
        secondSchedule.add(seconds);
        }
    //  System.out.println("Schedule Read Completed.");
      in.close();
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" +
            fileName + "'");
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '"
            + fileName + "'");
    }
  }
  public void readSong(String file) throws IOException{
    // Read from file to take all Image's names
    String fileName = file;
    try {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = in.readLine()) != null)
        {
        int seconds = Integer.parseInt(line);
        Song.add(seconds);
        }
        in.close();
      } catch(FileNotFoundException ex) {
          System.out.println(
              "Unable to open file '" +
              fileName + "'");
      }
      catch(IOException ex) {
          System.out.println(
              "Error reading file '"
              + fileName + "'");
      }
  }// Read Song ends

}
