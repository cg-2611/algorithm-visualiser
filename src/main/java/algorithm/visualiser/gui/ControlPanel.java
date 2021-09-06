package algorithm.visualiser.gui;

import javax.swing.JPanel;

public class ControlPanel extends JPanel {

    private int width;
    private int height;

    public ControlPanel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
