package algorithm.visualiser.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import algorithm.visualiser.util.Array;

public class RenderCanvas extends Canvas {

    private int width;
    private int height;

    private Array array;

    public RenderCanvas(int width, int height) {
        this.width = width;
        this.height = height;

        array = new Array(25);

        array.shuffle();

        this.setBackground(Color.DARK_GRAY);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void renderBar(Graphics g, int index, int height) {
        g.fillRect((index * 25) + 225 , 600 - height, 25, height);
    }

    public void renderBars(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < array.size(); i++) {
            renderBar(g, i, array.get(i));
        }
    }

}
