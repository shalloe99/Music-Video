import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Lyrics{

  ArrayList<String> Lyrics;

  public Lyrics(){
    Lyrics = new ArrayList<String>();
  }

  public void readLyrics(String file) throws IOException{
    // Read from file to take all Image's names
      String fileName = file;
      try {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = in.readLine()) != null)
        {
        Lyrics.add(line);
        }
  //    System.out.println("Lyrics Read Completed.");
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
  }// Read Lyrics ends
  public String wantLyrics(int index){
    return Lyrics.get(index);
  }
}// Lyrics class ends
