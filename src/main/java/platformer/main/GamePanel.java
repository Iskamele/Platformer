package platformer.main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import platformer.inputs.KeyBoardInputs;
import platformer.inputs.MouseInputs;

public class GamePanel extends JPanel {
    private final MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public void updateGame() {
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setPreferredSize(size);
    }

    public Game getGame(){
        return game;
    }
}
