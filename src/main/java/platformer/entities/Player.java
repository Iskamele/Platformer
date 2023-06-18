package platformer.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import static platformer.utilz.Constants.PlayerConstants.ATTACK_1;
import static platformer.utilz.Constants.PlayerConstants.GetSpriteAmount;
import static platformer.utilz.Constants.PlayerConstants.IDLE;
import static platformer.utilz.Constants.PlayerConstants.RUNNING;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = IDLE;
    private boolean moving, attacking = false;
    private boolean left, right, up, down;
    private float playerSpeed = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 128, 80, null);
    }

    private void setAnimation() {
        int startAnimation = playerAction;
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        if (attacking) {
            playerAction = ATTACK_1;
        }
        if (startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    private void updatePosition() {
        moving = false;
        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    private void loadAnimations() {
        String imagePathName = "/player_sprites.png";
        InputStream is = getClass().getResourceAsStream(imagePathName);
        try {
            if (is != null) {
                BufferedImage image = ImageIO.read(is);
                animations = new BufferedImage[9][6];
                for (int j = 0; j < animations.length; j++) {
                    for (int i = 0; i < animations[j].length; i++) {
                        animations[j][i] = image.getSubimage(i * 64, j * 40, 64, 40);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't import image: " + imagePathName, e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't close InputStream", e);
            }
        }

    }

    public void resetDirectionBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }
}
