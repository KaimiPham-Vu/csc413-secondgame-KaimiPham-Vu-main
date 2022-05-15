package ObjectsCtrl;

import Enum.Objects;
import GameObjects.FallingBlock;
import GameObjects.GameObject;


public class FallingObjectCtrl {
  private GameCtrl handler;
  private Objects nextInQueue;
  
  public FallingObjectCtrl(GameCtrl handler) {
    this.handler = handler;
  }
  
  public void createFallingObjectCtrl(Objects nextBlock) {
    boolean create = true;
    boolean playerCreated = false;
    GameObject tmpGameObject;
    Objects randomID;
    int x = 0;
    
    for (int i = 0; i < handler.obj.size(); i++) {
      tmpGameObject = handler.obj.get(i);
      
      if (tmpGameObject.getID() == Objects.Player) {
        x = tmpGameObject.getX();
        playerCreated = true;
        if(tmpGameObject.isMoving())
          create = false;
      } else if (tmpGameObject.isFalling()) {
        create = false;
      }
    }
    
    
    if (create && playerCreated) {
      handler.addObject(new FallingBlock(x, 0, nextBlock, handler));
      nextInQueue = randomID = Objects.randomFallingBlock();
    }
    
  }
  
  public Objects getNextInQueue() {
    return nextInQueue;
  }
}