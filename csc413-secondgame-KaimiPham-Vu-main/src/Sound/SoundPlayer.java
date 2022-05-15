package Sound;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundPlayer {
  public void playSound(String filename) {
    
    try {
      Clip clip = AudioSystem.getClip();
      URL file = this.getClass().getResource(filename);
      
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
      clip.open(inputStream);
      
      clip.start();
 
    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
  }
}