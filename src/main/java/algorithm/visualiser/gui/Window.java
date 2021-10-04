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

    /**
     * Creates a new Window object.
     * @param title JFrame title
     * @param width JFrame width
     * @param height JFrame height
     */
    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        initialise();
    }

    /**
     *
     * @return the RenderCanvas object associated with the window
     */
    public RenderCanvas getRenderCanvas() {
        return renderCanvas;
    }

    /**
     *
     * @return the ControlPanel object associated with the window
     */
    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    private void initialise() {
        // set JFrame properties
        setTitle(title);
        getContentPane().setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(700, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // instantiate the RenderCanvas object
        renderCanvas = new RenderCanvas();
        add(renderCanvas, BorderLayout.CENTER);

        // instantiate the ControlPanel object
        controlPanel = new ControlPanel(renderCanvas);
        controlPanel.setPreferredSize(new Dimension(width, 75));
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }

}
