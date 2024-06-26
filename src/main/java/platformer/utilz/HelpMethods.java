package platformer.utilz;

import java.awt.geom.Rectangle2D;
import platformer.main.Game;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height,
                                      int[][] levelData) {
        if (!isSolid(x, y, levelData)) {
            if (!isSolid(x + width, y + height, levelData)) {
                if (!isSolid(x + width, y, levelData)) {
                    if (!isSolid(x, y + height, levelData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSolid(float x, float y, int[][] levelData) {
        int maxWidth = levelData[0].length * Game.TILES_SIZE;
        if (x < 0 || x >= maxWidth) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;
        int value = levelData[(int) yIndex][(int) xIndex];
        return value >= 48 || value < 0 || value != 11;
    }

    public static float getEntityXPositionNextToWall(Rectangle2D.Float hitBox, float speedX) {
        int currentTile = (int) (hitBox.x / Game.TILES_SIZE);
        if (speedX > 0) {
            // Right
            int tilePosition = currentTile * Game.TILES_SIZE;
            int offsetX = (int) (Game.TILES_SIZE - hitBox.width);
            return tilePosition + offsetX - 1;
        } else {
            // Left
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float getEntityYPositionUnderRoofOrAboveFloor(Rectangle2D.Float hitBox,
                                                                float airSpeed) {
        int currentTile = (int) (hitBox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            // Falling - touching floor
            int tileYPosition = currentTile * Game.TILES_SIZE;
            int offsetY = (int) (Game.TILES_SIZE - hitBox.height);
            return tileYPosition + offsetY - 1;
        } else {
            // Jumping
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
        // Check the pixel below bottomleft and bottomright
        if (!isSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData)) {
            if (!isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData)) {
                return false;
            }
        }
        return true;
    }
}
