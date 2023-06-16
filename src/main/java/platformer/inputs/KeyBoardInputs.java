package platformer.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import platformer.main.GamePanel;

public class KeyBoardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.changeYDelta(-5);
                break;
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(5);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(5);
                break;
        }
    }
}