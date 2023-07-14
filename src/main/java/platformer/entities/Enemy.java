package platformer.entities;

import java.awt.geom.Rectangle2D;
import static platformer.utilz.Constants.EnemyConstants.GetSpriteAmount;

public abstract class Enemy extends Entity {
    private int animationIndex;
    private int enemyState;
    private int enemyType;
    private int animationTick = 25;
    private int animationSpeed = 25;

    public Enemy(float xCoordinate, float yCoordinate, int width, int height, int enemyType) {
        super(xCoordinate, yCoordinate, width, height);
        this.enemyType = enemyType;
        hitBox = new Rectangle2D.Float(xCoordinate, yCoordinate, width, height);
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(enemyType, enemyState)) {
                animationIndex = 0;
            }
        }
    }

    public void update() {
        updateAnimationTick();
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }
}
