package algorithm.visualiser.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {

    private String title;
    private int width;
    private int height;

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        initialise();
    }

    private void initialise() {
        this.setTitle(this.title);
        this.setSize(new Dimension(this.width, this.height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }

}
