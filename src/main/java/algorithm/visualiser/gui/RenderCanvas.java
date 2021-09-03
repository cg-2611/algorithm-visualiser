package algorithm.visualiser.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import algorithm.visualiser.util.Array;

public class RenderCanvas extends Canvas {

    private static final int HORIZONTAL_PADDING = 25;
    private static final int VERTICAL_PADDING = 50;

    private int width;
    private int height;

    private Array array;

    private int barWidth;

    public RenderCanvas(int width, int height) {
        this.width = width;
        this.height = height;

        array = new Array(50, height - (VERTICAL_PADDING * 2));
        array.shuffle();

        barWidth = (width - (HORIZONTAL_PADDING * 2)) / array.size();

        setBackground(Color.WHITE);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Array getArray() {
        return array;
    }

    public void renderBars(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < array.size(); i++) {
            renderBar(g, i, array.get(i));
        }
    }

    public void renderBar(Graphics g, int index, int barHeight) {
        g.fillRect((index * barWidth) + HORIZONTAL_PADDING + barWidth, height - VERTICAL_PADDING - barHeight, barWidth, barHeight);
    }

    public void renderCurrentBar(Graphics g, int index) {
        g.setColor(Color.RED);
        renderBar(g, index, array.get(index));
    }

}
