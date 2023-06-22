package platformer.gamestats;

import java.awt.event.MouseEvent;
import platformer.main.Game;
import platformer.ui.MenuButton;

public class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public boolean isIm(MouseEvent e, MenuButton menuButton) {
        return menuButton.getBounds().contains(e.getX(), e.getY());
    }
}
