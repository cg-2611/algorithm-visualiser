package algorithm.visualiser.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {

    private String title;
    private int width;
    private int height;

    private RenderCanvas renderCanvas;
    private ControlPanel controlPanel;

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        initialise();
    }

    private void initialise() {
        setTitle(title);
        getContentPane().setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        renderCanvas = new RenderCanvas(width, height);
        add(renderCanvas, BorderLayout.CENTER);

        controlPanel = new ControlPanel(width, 50);
        controlPanel.setPreferredSize(new Dimension(width, 50));
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }

    public RenderCanvas getRenderCanvas() {
        return renderCanvas;
    }

}
