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

    public Visualiser(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        running = false;
    }

    public void initialise() {
        window = new Window(this.title, this.width, this.height);
        window.setVisible(true);

        renderCanvas = window.getRenderCanvas();
        controlPanel = window.getControlPanel();

        if (renderCanvas.getBufferStrategy() == null) {
            renderCanvas.createBufferStrategy(3);
        }
    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;

        thread = new Thread(this);
        thread.start();
    }

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

    public void update() {
        activeIndexes = controlPanel.getAlgorithmSelection().getActiveIndexes();
    }

    public void render() {
        bs = renderCanvas.getBufferStrategy();
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);

        renderCanvas.renderBars(g);

        if (controlPanel.getAlgorithmRunning()) {
            for (int i = 0; i < activeIndexes.length; i++) {
                renderCanvas.renderActiveBar(g, activeIndexes[i]);
            }
        }

        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        initialise();

        while (running) {
            if (controlPanel.getAlgorithmRunning()) {
                update();
            }

            render();
        }

        stop();
    }

}
