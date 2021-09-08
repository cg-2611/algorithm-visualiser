package algorithm.visualiser.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import algorithm.visualiser.util.Array;

public class RenderCanvas extends Canvas {

    private Array array;

    public RenderCanvas() {
        array = new Array(10);

        setBackground(Color.WHITE);
    }

    public Array getArray() {
        return array;
    }

    public void renderBars(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < array.size(); i++) {
            renderBar(g, i);
        }
    }

    public void renderBar(Graphics g, int index) {
        int barWidth = getWidth() / array.size();
        int barHeight = ((getHeight() - 5) / array.size()) * array.get(index);

        int x = barWidth * index;
        int y = getHeight() - barHeight;

        g.fillRect(x, y, barWidth, barHeight);
    }

    public void renderActiveBar(Graphics g, int index) {
        g.setColor(Color.RED);
        renderBar(g, index);
    }

}
