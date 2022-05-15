package Images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {
  private BufferedImage image;
  
  public BufferedImage loadImage(String source) {
    try {
      image = ImageIO.read(getClass().getResourceAsStream(source));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return image;
  }
}