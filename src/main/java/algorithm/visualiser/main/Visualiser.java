package algorithm.visualiser.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import algorithm.visualiser.entities.Bar;
import algorithm.visualiser.gui.RenderCanvas;
import algorithm.visualiser.gui.Window;

public class Visualiser implements Runnable {

    private String title;
    private int width;
    private int height;

    private Window window;
    private RenderCanvas renderCanvas;

    private Boolean running;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    public Visualiser(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        this.running = false;
    }

    public void initialise() {
        window = new Window(this.title, this.width, this.height);
        window.setVisible(true);

        renderCanvas = window.getRenderCanvas();
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

    public void render() {
        bs = renderCanvas.getBufferStrategy();

        if (bs == null) {
            renderCanvas.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);

        renderCanvas.renderBars(g);

        bs.show();

        g.dispose();
    }

    @Override
    public void run() {
        initialise();

        while (running) {
            // update
            render();
        }

        stop();
    }

}