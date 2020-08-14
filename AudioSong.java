import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class AudioSong{
    File file;
    Clip clip;
    AudioInputStream ais;
    long clipTime;
  public AudioSong(){
    try{
      file = new File("song.wav");
      clip = AudioSystem.getClip();
      ais = AudioSystem.getAudioInputStream(file);
      clip.open(ais);
      clipTime = 0;
  }catch(Exception exception){System.out.println("Failed To Play The WAV File!");}
  }// Constructor ends
  public void start(){
    clip.start();
  //  clip.loop(Clip.LOOP_CONTINUOUSLY);
  }
  public void stop(){
    clipTime = clip.getMicrosecondPosition();
    clip.stop();
  }
  public void resume(){
    clip.setMicrosecondPosition(clipTime);
    clip.start();
  }
  public void reset(){
    clip.setMicrosecondPosition(0);
  }
}//Audio class ends
