package algorithm.visualiser.main;

import algorithm.visualiser.gui.Window;

public class Visualiser implements Runnable {

    private String title;
    private int width;
    private int height;

    private Window window;

    private Boolean running;
    private Thread thread;

    public Visualiser(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        this.running = false;
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

    @Override
    public void run() {
        window = new Window(this.title, this.width, this.height);
        window.setVisible(true);

        stop();
    }

}
