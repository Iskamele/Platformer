package platformer.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import platformer.gamestats.Playing;
import static platformer.utilz.Constants.EnemyConstants.CRABBY_HEIGHT;
import static platformer.utilz.Constants.EnemyConstants.CRABBY_HEIGHT_DEFAULT;
import static platformer.utilz.Constants.EnemyConstants.CRABBY_WIDTH;
import static platformer.utilz.Constants.EnemyConstants.CRABBY_WIDTH_DEFAULT;
import platformer.utilz.LoadSave;

public class EnemyHandler {

    private Playing playing;
    private BufferedImage[][] crabbyArray;
    private ArrayList<Crabby> crabbies = new ArrayList<>();

    public EnemyHandler(Playing playing) {
        this.playing = playing;
        loadEnemyImages();
        addEnemies();
    }

    private void addEnemies() {
        crabbies = LoadSave.GetCrabs();
    }

    public void update() {
        for (Crabby crabby : crabbies) {
            crabby.update();
        }
    }

    public void draw(Graphics g, int xLevelOffset) {
        drawCrabs(g, xLevelOffset);
    }

    private void drawCrabs(Graphics g, int xLevelOffset) {
        for (Crabby crabby : crabbies) {
            g.drawImage(crabbyArray[crabby.getEnemyState()][crabby.getAnimationIndex()],
                    (int) crabby.getHitBox().x - xLevelOffset, (int) crabby.getHitBox().y,
                    CRABBY_WIDTH, CRABBY_HEIGHT, null);
        }
    }

    private void loadEnemyImages() {
        crabbyArray = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.CRABBY_SPRITE);
        for (int i = 0; i < crabbyArray.length; i++) {
            for (int j = 0; j < crabbyArray[i].length; j++) {
                crabbyArray[i][j] = temp.getSubimage(
                        j * CRABBY_WIDTH_DEFAULT,
                        i * CRABBY_HEIGHT_DEFAULT,
                        CRABBY_WIDTH_DEFAULT,
                        CRABBY_HEIGHT_DEFAULT);
            }
        }
    }
}
