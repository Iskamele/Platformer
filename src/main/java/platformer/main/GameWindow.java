package platformer.main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();

        // window frame size
        jFrame.setSize(400, 400);

        // set window visible
        jFrame.setVisible(true);

        // add game panel to frame
        jFrame.add(gamePanel);

        // close app
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
