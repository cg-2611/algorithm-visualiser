package algorithm.visualiser.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class RenderCanvas extends Canvas {

    private int width;
    private int height;

    private int[] array;

    public RenderCanvas(int width, int height) {
        this.width = width;
        this.height = height;

        array = new int[25];

        createArray();
        shuffleArray();

        this.setBackground(Color.DARK_GRAY);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void createArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = 50 + (i * 20);
        }
    }

    public void shuffleArray() {
        Random random = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public void renderBar(Graphics g, int index, int height) {
        g.fillRect((index * 25) + 225 , 600 - height, 25, height);
    }

    public void renderBars(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < array.length; i++) {
            renderBar(g, i, array[i]);
        }
    }

}
