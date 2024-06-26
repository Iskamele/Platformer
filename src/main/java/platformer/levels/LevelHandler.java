package platformer.levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import platformer.main.Game;
import platformer.utilz.LoadSave;

public class LevelHandler {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelHandler(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = image.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g, int levelOffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < levelOne.getLevelData()[0].length; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index],
                        Game.TILES_SIZE * i - levelOffset,
                        Game.TILES_SIZE * j,
                        Game.TILES_SIZE,
                        Game.TILES_SIZE,
                        null);
            }
        }
    }

    public void update() {
    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
