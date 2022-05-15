package ObjectsCtrl;

import Enum.Objects;
import GameObjects.GameObject;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class GameCtrl {
  public ArrayList<GameObject> obj = new ArrayList<GameObject>();
  static HashMap<Objects, String> blockImageMap = new HashMap<>();

  private boolean moveRight = false;
  private boolean moveLeft = false;
  private boolean victory = false;
  private boolean resetLevel = false;
  private boolean nextLevel = false;

  public void tick() {
    for (int i = 0; i < obj.size(); i++) {
      GameObject tmpObj = obj.get(i);
      tmpObj.tick();
    }
  }

  public void render(Graphics graphics) {
    for (int i = 0; i < obj.size(); i++) {
      GameObject tmpObj = obj.get(i);
      tmpObj.render(graphics);
    }
  }

  public void addObject(GameObject tmpObj) {
    obj.add(tmpObj);
  }

  public void removeObject(GameObject tmpObj) {
    obj.remove(tmpObj);
  }


  public boolean nextLevel() {return nextLevel; }
  public void setnextLevel(boolean next) {nextLevel = next;}
  public boolean victory() {
    return victory;
  }
  public void setVictory(boolean v) {
    victory = v;
  }
  public boolean resetLevel() {
    return resetLevel;
  }
  public void setResetLevel(boolean reset) {
    resetLevel = reset;
  } 
  public boolean isRight() {
    return moveRight;
  }
  public boolean isLeft() {
    return moveLeft;
  }
  public void setRight(boolean right) {
    this.moveRight = right;
  }
  public void setLeft(boolean left) {
    this.moveLeft = left;
  }
  public String getImageSource(Objects id) {
    return blockImageMap.get(id);
  }
  
  static {
    blockImageMap.put(Objects.CardboardBox, "/resources/CardBox.png");
    blockImageMap.put(Objects.Rock, "/resources/Rock.png");
    blockImageMap.put(Objects.Mesh, "/resources/Mesh.png");
    blockImageMap.put(Objects.MetalBox, "/resources/MetalBox.png");
    blockImageMap.put(Objects.StoneBox, "/resources/StoneBox.png");
    blockImageMap.put(Objects.WoodBox, "/resources/WoodBox.png");
    blockImageMap.put(Objects.WoodBox, "/resources/heart.png");
    blockImageMap.put(Objects.WoodBox, "/resources/Bomb.png");
  }
}