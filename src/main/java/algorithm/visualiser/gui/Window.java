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
        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        renderCanvas = new RenderCanvas();
        add(renderCanvas, BorderLayout.CENTER);

        controlPanel = new ControlPanel();
        controlPanel.setPreferredSize(new Dimension(width, (int) height / 10));
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }

    public RenderCanvas getRenderCanvas() {
        return renderCanvas;
    }

}
