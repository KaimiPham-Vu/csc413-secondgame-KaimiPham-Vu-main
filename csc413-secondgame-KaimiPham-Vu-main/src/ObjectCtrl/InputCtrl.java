package ObjectsCtrl;

import Enum.Objects;
import GameObjects.GameObject;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputCtrl extends KeyAdapter {
  GameCtrl handler;
  
  public InputCtrl(GameCtrl handler) {
    this.handler = handler;
  }
  
  public void keyPressed(KeyEvent event) {
    int key = event.getKeyCode();
    
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
      if (tmpObj.getID() == Objects.Player) {
        if (key == KeyEvent.VK_A) handler.setLeft(true);
        if (key == KeyEvent.VK_D) handler.setRight(true);
      }
    }
  }
  
  public void keyReleased(KeyEvent event) {
    int key = event.getKeyCode();
    
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);

      if (tmpObj.getID() == Objects.Player) {
        if (key == KeyEvent.VK_A) handler.setLeft(false);
        if (key == KeyEvent.VK_D) handler.setRight(false);
      }
    }
  }
}