package platformer.gamestats;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import platformer.entities.Player;
import platformer.levels.LevelHandler;
import platformer.main.Game;

public class Playing extends State implements StateMethods {
    private Player player;
    private LevelHandler levelHandler;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelHandler = new LevelHandler(game);
        player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        player.loadLevelData(levelHandler.getCurrentLevel().getLevelData());
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }

    @Override
    public void update() {
        levelHandler.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        levelHandler.draw(g);
        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
            case KeyEvent.VK_BACK_SPACE:
                GameState.state = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
        }
    }
}