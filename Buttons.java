import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Buttons{
  ArrayList<BufferedImage> Buttons;
  ArrayList<String> buttonsNames;
  private static final int IMG_WIDTH = 1200;
	private static final int IMG_HEIGHT = 800;
  public Buttons(){
    Buttons = new ArrayList<BufferedImage>();
    buttonsNames = new ArrayList<String>();
  }

  public void readButtons(String file) throws IOException{
    // Read from file to take all buttons's names
      String fileName = file;
      try {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = in.readLine()) != null)
        {
        buttonsNames.add(line);
        }
      //System.out.println("buttons Names Read Completed.");
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
    // Read and restore all the Buttons
    for(int i = 0;i <buttonsNames.size();i++){
      try{
        BufferedImage buttons = ImageIO.read(new File(buttonsNames.get(i)));
        //buttons = new Bufferedbuttons(IMG_WIDTH, IMG_HEIGHT,buttons,Bufferedbuttons.TYPE_INT_RGB);
        Buttons.add(buttons);
      }
      catch(FileNotFoundException ex) {
          System.out.println(
              "Unable to open file '" +
              buttonsNames.get(i) + "'");
      }
      catch(IOException ex) {
          System.out.println(
              "Error reading file '"
              + buttonsNames.get(i) + "'");
      }
    }
    System.out.println("buttons Restore Completed.");
  }// Method readbuttons ends

  public BufferedImage wantButtons(int index){
    return Buttons.get(index);
  }
}
