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

    public RenderCanvas getRenderCanvas() {
        return renderCanvas;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    private void initialise() {
        setTitle(title);
        getContentPane().setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(700, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        renderCanvas = new RenderCanvas();
        add(renderCanvas, BorderLayout.CENTER);

        controlPanel = new ControlPanel(renderCanvas);
        controlPanel.setPreferredSize(new Dimension(width, 75));
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }

}
