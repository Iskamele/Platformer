package platformer.main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import platformer.inputs.KeyBoardInputs;
import platformer.inputs.MouseInputs;
import static platformer.main.Game.GAME_HEIGHT;
import static platformer.main.Game.GAME_WIDTH;

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

    public Game getGame() {
        return game;
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }
}
