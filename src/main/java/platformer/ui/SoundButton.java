package platformer.ui;

import static platformer.utilz.Constants.UI.PauseButtons.SOUND_DEFAULT_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import platformer.utilz.LoadSave;

public class SoundButton extends PauseButton implements DrawAndUpdate {

    private BufferedImage[][] soundImages;
    private boolean mouseOver;
    private boolean mousePressed;
    private boolean muted;
    private int rowIndex;
    private int columnIndex;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        loadSoundImages();
    }

    private void loadSoundImages() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImages = new BufferedImage[2][3];
        for (int j = 0; j < soundImages.length; j++) {
            for (int i = 0; i < soundImages[j].length; i++) {
                soundImages[j][i] = temp.getSubimage(
                        i * SOUND_DEFAULT_SIZE,
                        j * SOUND_DEFAULT_SIZE,
                        SOUND_DEFAULT_SIZE,
                        SOUND_DEFAULT_SIZE);
            }
        }
    }

    @Override
    public void update() {
        if (muted) {
            rowIndex = 1;
        } else {
            rowIndex = 0;
        }
        columnIndex = 0;
        if (mouseOver) {
            columnIndex = 1;
        }
        if (mousePressed) {
            columnIndex = 2;
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(soundImages[rowIndex][columnIndex], x, y, width, height, null);
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

}
