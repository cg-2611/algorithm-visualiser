package algorithm.visualiser.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import algorithm.visualiser.entities.BarArray;

public class RenderCanvas extends Canvas {

    private int width;
    private int height;

    private BarArray barArray;

    public RenderCanvas(int width, int height) {
        this.width = width;
        this.height = height;

        barArray = new BarArray(25, 10, 50);

        this.setBackground(Color.DARK_GRAY);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void renderBars(Graphics g) {
        for (int i = 0; i < barArray.getSize(); i++) {
            barArray.getBar(i).draw(i, g);
        }
    }

}
