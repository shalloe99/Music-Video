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

public class Image{
  ArrayList<BufferedImage> Images;
  ArrayList<String> ImageNames;
  private static final int IMG_WIDTH = 1200;
	private static final int IMG_HEIGHT = 800;
  public Image(){
    Images = new ArrayList<BufferedImage>();
    ImageNames = new ArrayList<String>();
  }

  public void readImage(String file) throws IOException{
    // Read from file to take all Image's names
      String fileName = file;
      try {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = in.readLine()) != null)
        {
        ImageNames.add(line);
        }
      //System.out.println("Image Names Read Completed.");
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
    // Read and restore all the Images
    for(int i = 0;i <ImageNames.size();i++){
      try{
        BufferedImage image = ImageIO.read(new File(ImageNames.get(i)));
        Images.add(image);
      }
      catch(FileNotFoundException ex) {
          System.out.println(
              "Unable to open file '" +
              ImageNames.get(i) + "'");
      }
      catch(IOException ex) {
          System.out.println(
              "Error reading file '"
              + ImageNames.get(i) + "'");
      }
    }
//    System.out.println("Image Restore Completed.");
  }// Method readImage ends

  public BufferedImage wantImage(int index){
    return Images.get(index);
  }
}
