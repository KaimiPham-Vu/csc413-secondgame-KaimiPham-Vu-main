package Enum;


import java.util.ArrayList;
import java.util.Random;

public enum Objects {
    Player(),
            StopButton(),
            CardboardBox(),
            Mesh(),
            MetalBox(),
            Rock(),
            StoneBox(),
            Wall(),
            WoodBox(),
            Heart(),
            Bomb;

private static final ArrayList<Objects> fallingBlockList = new ArrayList<>();

static {
        fallingBlockList.add(CardboardBox);
        fallingBlockList.add(Mesh);
        fallingBlockList.add(MetalBox);
        fallingBlockList.add(Rock);
        fallingBlockList.add(StoneBox);
        fallingBlockList.add(WoodBox);
        fallingBlockList.add(Heart);
        fallingBlockList.add(Bomb);
        }

private static final int size = fallingBlockList.size();
private static final Random random = new Random();

public static Objects randomFallingBlock() {
        return fallingBlockList.get(random.nextInt(size));
        }

public static boolean isAFallingBlock(Objects id) {
        if (fallingBlockList.contains(id)) {
        return true;
        }
        return false;
        }
}