package algorithm.visualiser.main;

import javax.swing.SwingUtilities;

public class Launcher {

    private static final String TITLE = "Algorithm Visualiser";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;

    public static void main(String[] args) {
        Visualiser visualiser = new Visualiser(TITLE, WIDTH, HEIGHT);

        SwingUtilities.invokeLater(() -> {
            visualiser.start();
        });
    }

}
