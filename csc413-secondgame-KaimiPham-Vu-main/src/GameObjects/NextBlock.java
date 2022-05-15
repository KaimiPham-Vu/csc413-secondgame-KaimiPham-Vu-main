package GameObjects;

import Enum.Objects;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;

public abstract class NextBlock extends GameObject {
  protected static HashMap<Objects, Integer> blockWeightMap = new HashMap<>();
  protected static HashMap<Objects, Integer> blockSpeedMap = new HashMap<>();
  
  public NextBlock(int x, int y, Objects id) {
    super(x, y, id);
  }
  
  public abstract void tick();
  public abstract void render(Graphics graphics);
  public abstract Rectangle getBounds();
  
  static {
    blockSpeedMap.put(Objects.CardboardBox, 5);
    blockSpeedMap.put(Objects.Rock, 7);
    blockSpeedMap.put(Objects.Mesh, 5);
    blockSpeedMap.put(Objects.MetalBox, 8);
    blockSpeedMap.put(Objects.StoneBox, 9);
    blockSpeedMap.put(Objects.WoodBox, 6);
    blockSpeedMap.put(Objects.Heart, 11);
    blockSpeedMap.put(Objects.Bomb, 12);

    blockWeightMap.put(Objects.CardboardBox, 1);
    blockWeightMap.put(Objects.Rock, 3);
    blockWeightMap.put(Objects.Mesh, 0);
    blockWeightMap.put(Objects.MetalBox, 4);
    blockWeightMap.put(Objects.StoneBox, 5);
    blockWeightMap.put(Objects.WoodBox, 2);
    blockWeightMap.put(Objects.Heart, 1);
    blockWeightMap.put(Objects.Bomb, 0);
  }
}