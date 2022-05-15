package GameObjects;

import Enum.Objects;
import Enum.PlayerStates;
import Images.ImageStripLoader;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import ObjectsCtrl.GameCtrl;

public class Player extends GameObject {
  
  private HashMap<PlayerStates, BufferedImage[]> images;
  private ImageStripLoader imgStripLoader;
 
  private BufferedImage[] blobStrip;
  GameCtrl handler;
  
  private int movesLeft; 
  private boolean movingLeft = false;
  private boolean movingRight = false;
  
  public Player(int x, int y, Objects id, GameCtrl handler) {
    super(x, y, id);
    this.handler = handler;
    lives = 3;
    
    images = new HashMap<>();
    imgStripLoader = new ImageStripLoader();
    fillImageMap();
    
    movesLeft = 0;
    blobStrip = images.get(PlayerStates.Standing);
  }
  
  @Override
  public void tick() {
    
    if (movesLeft > 0 ) {
      x += xVelocity;
      y += yVelocity;
      
      collision();
      movesLeft--;
    } else {
      //reset image
      isMoving = false;
      movingRight = false;
      movingLeft = false;
      blobStrip = images.get(PlayerStates.Standing);
      movesLeft = 0;
      
      if (handler.isLeft()) {
        //moves a block
        xVelocity = -6;
        movesLeft = 7;
        movingLeft = true;
        isMoving = true;
        
        soundPlayer.playSound(playerStateSound.get(PlayerStates.MoveLeft));
        blobStrip = images.get(PlayerStates.MoveLeft);
        
      } else if (!handler.isRight()) {
        xVelocity = 0;
        yVelocity = 0;
      }
    
      if (handler.isRight()) {
        xVelocity = 6;
        movesLeft = 7;
        movingRight = true;
        isMoving = true;
        
        soundPlayer.playSound(playerStateSound.get(PlayerStates.MoveRight));
        blobStrip = images.get(PlayerStates.MoveRight);
      } else if (!handler.isLeft()) {
        xVelocity = 0;
        yVelocity = 0;
      }
    }
    
    if (this.lives == 0) {
      handler.removeObject(this);
    }
  }
  
  @Override
  public void render(Graphics graphics) {
    int tmpIndex;
    
    if (movesLeft == 0) {
      tmpIndex = blobStrip.length - 1;
    } else {
      tmpIndex = blobStrip.length - movesLeft;
    }
    
    if (movingRight) {     
      graphics.drawImage(blobStrip[tmpIndex], x - xVelocity*tmpIndex, y - 42, blobStrip[tmpIndex].getWidth(), blobStrip[tmpIndex].getHeight(), null);   
    } else if (movingLeft) {
      graphics.drawImage(blobStrip[tmpIndex], x - xVelocity*tmpIndex - 42, y - 42, blobStrip[tmpIndex].getWidth(), blobStrip[tmpIndex].getHeight(), null);
    } else {
      graphics.drawImage(blobStrip[tmpIndex], x, y, blobStrip[tmpIndex].getWidth(), blobStrip[tmpIndex].getHeight(), null);
    }
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, width, height);
  }
 
  private void collision() {
    boolean intersect = false;
    
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
      if (tmpObj.getID() != Objects.Player && tmpObj.getID() != Objects.StopButton){
        if (getBounds().intersects(tmpObj.getBounds())) {  
          y -= 42; 
          
          for (int j = 0; j < handler.obj.size(); j++) {
            GameObject tmpObj2 = handler.obj.get(j);
     
            if (tmpObj2.getID() != Objects.Player && tmpObj.getID() != Objects.StopButton){
              if(getBounds().intersects(tmpObj2.getBounds()))
                intersect = true;   
              }

            if (tmpObj2.getID() == Objects.Bomb){
              if(getBounds().intersects(tmpObj2.getBounds()))
                intersect = true;
            }

            if (tmpObj2.getID() == Objects.StopButton){
              if(getBounds().intersects(tmpObj2.getBounds())){
                handler.setVictory(true);
                soundPlayer.playSound(gameObjectSound.get(Objects.StopButton));
              } 
            }
          }
            
          
          if (intersect == true){
            y += 42;
            x += -xVelocity;
          } else {
            y -= 0;
            x += 0;
          }
        }
      } 
    }
    
    y += 42;
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
      if (tmpObj.getID() != Objects.Player){
        if (getBounds().intersects(tmpObj.getBounds())) {
          y -= 42;
        }
      }
    }
    
  }
  
  public void fillImageMap() {
    images.put(PlayerStates.Standing, imgStripLoader.getImageStrip("/resources/Lazarus_stand.png", 1));
    images.put(PlayerStates.Scared, imgStripLoader.getImageStrip("/resources/Lazarus_afraid_strip10.png", 10));
    images.put(PlayerStates.Squished, imgStripLoader.getImageStrip("/resources/Lazarus_squished_strip11.png", 11));
    images.put(PlayerStates.MoveLeft, imgStripLoader.getImageStrip("/resources/Lazarus_left_strip7.png", 7));
    images.put(PlayerStates.MoveRight, imgStripLoader.getImageStrip("/resources/Lazarus_right_strip7.png", 7));
    images.put(PlayerStates.JumpLeft, imgStripLoader.getImageStrip("/resources/Lazarus_jump_left_strip7.png", 7));
    images.put(PlayerStates.JumpRight, imgStripLoader.getImageStrip("/resources/Lazarus_jump_right_strip7.png", 7));
  }
}