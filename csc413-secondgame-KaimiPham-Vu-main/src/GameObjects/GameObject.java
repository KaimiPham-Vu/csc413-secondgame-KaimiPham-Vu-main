package GameObjects;

import Enum.Objects;
import Enum.PlayerStates;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import Sound.SoundPlayer;

public abstract class GameObject {
  protected int width = 42;
  protected int height = 42;
  protected int x, y;
  protected int xVelocity = 0;
  protected int yVelocity = 0;
  protected int lives;
  protected Objects id;
  protected boolean isFalling = false;
  protected boolean isMoving = false;
  protected SoundPlayer soundPlayer = new SoundPlayer();
  
  protected static HashMap<PlayerStates, String> playerStateSound = new HashMap<>();
  protected static HashMap<Objects, String> gameObjectSound = new HashMap<>();

  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  public int getWidth() {
    return width;
  }
  public int getHeigh() {
    return height;
  }
  public void setX(int x) {
    this.x = x;
  }
  public void setY(int y) {
    this.y = y;
  }
  public void setWidth(int w) {
    this.width = w;
  }
  public void setHeight(int h) {
    this.height = h;
  }
  public Objects getID() {
    return id;
  }
  public void setID(Objects id) {
    this.id = id;
  }
  public boolean isFalling() {
    return isFalling;
  }
  public boolean isMoving() {
    return isMoving;
  }
  public void setFalling(boolean fall) {
    this.isFalling = fall;
  }
  public void setLives(int lives) {
    this.lives = lives;
  }
  public int getLives() {
    return lives;
  }

  public GameObject(int x, int y, Objects id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }
  
  public abstract void tick();
  public abstract void render(Graphics graphics);
  public abstract Rectangle getBounds();
  
  static {
    playerStateSound.put(PlayerStates.JumpLeft, "/resources/Move.wav");
    playerStateSound.put(PlayerStates.JumpRight, "/resources/Move.wav");
    playerStateSound.put(PlayerStates.MoveLeft, "/resources/Move.wav");
    playerStateSound.put(PlayerStates.MoveRight, "/resources/Move.wav");
    playerStateSound.put(PlayerStates.Squished, "/resources/Move.wav");
    gameObjectSound.put(Objects.StopButton, "/resources/Button.wav");
  }
}