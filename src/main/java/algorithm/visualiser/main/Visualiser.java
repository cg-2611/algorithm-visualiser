package algorithm.visualiser.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import algorithm.visualiser.gui.ControlPanel;
import algorithm.visualiser.gui.RenderCanvas;
import algorithm.visualiser.gui.Window;

public class Visualiser implements Runnable {

    private String title;
    private int width;
    private int height;

    private Window window;
    private RenderCanvas renderCanvas;
    private ControlPanel controlPanel;

    private Boolean running;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private int[] activeIndexes;

    /**
     * Creates a Visualiser object with the specified title, width and height.
     * @param title the title of the window
     * @param width the width of the window
     * @param height the height of the window
     */
    public Visualiser(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        running = false;
    }

    private void initialise() {
        // create the window and panels that make up the GUI
        window = new Window(this.title, this.width, this.height);
        window.setVisible(true);

        renderCanvas = window.getRenderCanvas();
        controlPanel = window.getControlPanel();

        if (renderCanvas.getBufferStrategy() == null) {
            renderCanvas.createBufferStrategy(3);
        }
    }

    /**
     * Starts the visualiser thread.
     */
    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;

        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops the visualiser thread.
     */
    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;

        try{
            thread.join();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the state of the visualiser by getting the active indexes of the current algorithm.
     */
    public void update() {
        if (controlPanel.getAlgorithmTypeSelection().equals("Sorting")) {
            activeIndexes = controlPanel.getSortingAlgorithm().getActiveIndexes();
        } else if (controlPanel.getAlgorithmTypeSelection().equals("Searching")) {
            activeIndexes = controlPanel.getSearchingAlgorithm().getActiveIndexes();
        }
    }

    /**
     * Renders the bars in the visualiser and any active bars as well.
     */
    public void render() {
        bs = renderCanvas.getBufferStrategy();
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);

        renderCanvas.renderBars(g);

        if (controlPanel.isAlgorithmRunning()) {
            for (int i = 0; i < activeIndexes.length; i++) {
                renderCanvas.renderActiveBar(g, activeIndexes[i], i);
            }
        }

        if (controlPanel.getAlgorithmTypeSelection().equals("Searching") && controlPanel.getSearchingAlgorithm() != null) {
            if (controlPanel.getSearchingAlgorithm().getFoundIndex() != -1) {
                renderCanvas.renderTargetBar(g, controlPanel.getSearchingAlgorithm().getFoundIndex());
            }
        }

        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        initialise();

        while (running) {
            if (controlPanel.isAlgorithmRunning()) {
                update();
            }

            render();
        }

        stop();
    }

}
