package platformer.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float xCoordinate;
    protected float yCoordinate;
    protected int width;
    protected int height;
    protected Rectangle2D.Float hitBox;

    public Entity(float xCoordinate, float yCoordinate, int width, int height) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = width;
        this.height = height;
    }

    protected void drawHitBox(Graphics g) {
        // For debugging the hitBox
        g.setColor(Color.MAGENTA);
        g.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    protected void initHitBox(float x, float y, int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
}
