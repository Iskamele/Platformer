package platformer.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import platformer.main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gamePanel.setRectPosition(e.getX(), e.getY());
    }
}
