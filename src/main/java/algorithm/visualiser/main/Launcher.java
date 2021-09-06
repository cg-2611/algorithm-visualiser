package algorithm.visualiser.main;

import java.awt.Toolkit;

import javax.swing.SwingUtilities;

public class Launcher {

    private static final String TITLE = "Algorithm Visualiser";

    private static final int WIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
    private static final int HEIGHT = (WIDTH * 9) / 16;

    public static void main(String[] args) {
        Visualiser visualiser = new Visualiser(TITLE, WIDTH, HEIGHT);

        SwingUtilities.invokeLater(() -> {
            visualiser.start();
        });
    }

}
