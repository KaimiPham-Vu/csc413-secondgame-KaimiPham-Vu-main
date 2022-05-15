package LazarusMainGame;

import Images.ImageLoader;
import Enum.Objects;
import ObjectsCtrl.InputCtrl;
import ObjectsCtrl.GameCtrl;
import GameObjects.StopButton;
import GameObjects.Wall;
import ObjectsCtrl.FallingObjectCtrl;
import GameObjects.Player;
import java.awt.*;
import java.awt.image.*;

public class LazarusLevel2 extends LazarusGame {
    public static int WIDTH = 845;
    public static int HEIGHT = 701;

    private boolean isRunning = false;
    private Thread thread;
    private GameCtrl handler;
    private EndGame ending;
    private FallingObjectCtrl fallingObject;

    private BufferedImage level;
    private BufferedImage background;
    private BufferedImage lives;
    private BufferedImage nextBlockImage;
    private ImageLoader loader;

    private int playerLives;
    private int resetPosX;
    private int resetPosY;
    private boolean victory = false;
    private boolean gameOver = false;
    private String message;
    private Objects nextBlockQueued;

    public LazarusLevel2() {
        new GameScreen(WIDTH, HEIGHT, "Lazarus", this);
        start();

        handler = new GameCtrl();
        this.addKeyListener(new InputCtrl(handler));
        fallingObject = new FallingObjectCtrl(handler);
        ending = new EndGame();
        nextBlockQueued = Objects.randomFallingBlock();

        loader = new ImageLoader();
        level = loader.loadImage("/resources/LazarusSimpleMap1.png");
        background = loader.loadImage("/resources/Background.png");
        lives = loader.loadImage("/resources/heart.png");

        loadLevel(level);
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        handler.tick();

        for (int i = 0; i < handler.obj.size(); i++) {
            if (handler.obj.get(i).getID() == Objects.Player) {
                playerLives = handler.obj.get(i).getLives();

                if (playerLives == 0) {
                    gameOver = true;
                    message = "GAME OVER";
                }

            }
        }

        if (handler.victory() && playerLives != 0) {
            message = "YOU WIN!!!";
        }


        if (handler.resetLevel()) {
            for (int i = 0; i < handler.obj.size(); i++) {
                if (Objects.isAFallingBlock(handler.obj.get(i).getID())) {
                    handler.removeObject(handler.obj.get(i));
                } else if (handler.obj.get(i).getID() == Objects.Player) {
                    handler.obj.get(i).setX(resetPosX);
                    handler.obj.get(i).setY(resetPosY);
                }
            }

            handler.setResetLevel(false);
        }

        if (playerLives > 0) {
            fallingObject.createFallingObjectCtrl(nextBlockQueued);
            nextBlockQueued = fallingObject.getNextInQueue();

            nextBlockImage = loader.loadImage(handler.getImageSource(nextBlockQueued));
        }
    }

    public void render() {
        BufferStrategy buffStrat = this.getBufferStrategy();

        if (buffStrat == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = buffStrat.getDrawGraphics();

        //Map Start

        //background
        graphics.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

        //lives
        if (playerLives > 0) {
            for (int i = 0; i < playerLives; i++){
                graphics.drawImage(lives, 10 + 35 * i, 10, 40, 40, null);
            }
        }

        //next block
        graphics.setColor(Color.black);
        graphics.fillRect(5, 120, 42*2, 42*2);
        graphics.setColor(Color.red);
        graphics.drawRect(5, 120, 42*2, 42*2);
        graphics.drawImage(nextBlockImage, 5 + 84/4, 120 + 84/4, 42, 42, null);

        if (gameOver || handler.victory()) {
            ending.loadEnding(graphics, WIDTH, HEIGHT, message);
        } else {
            handler.render(graphics);
        }
        // Map section end

        graphics.dispose();
        buffStrat.show();
    }

    public void loadLevel(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int xAxis = 0; xAxis < width; xAxis++) {
            for (int yAxis = 0; yAxis < height; yAxis++) {
                int pixel = image.getRGB(xAxis, yAxis);

                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255) {
                    handler.addObject(new Wall(xAxis * 42, yAxis * 42, Objects.Wall));
                }
                if (green == 255) {
                    handler.addObject(new Player(xAxis * 42, yAxis * 42, Objects.Player, handler));
                    resetPosX = xAxis * 42;
                    resetPosY = yAxis * 42;
                }
                if (blue == 255) {
                    handler.addObject(new StopButton(xAxis * 42, yAxis * 42, Objects.StopButton));
                }
            }
        }
    }

    //loop
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    public static void main(String args[]) {
        new LazarusLevel2();
    }

}
