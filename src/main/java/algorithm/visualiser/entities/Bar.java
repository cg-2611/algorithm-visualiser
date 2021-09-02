package algorithm.visualiser.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Bar {

    private int width;
    private int height;

    private Color colour;

    public Bar(int width, int height) {
        this.width = width;
        this.height = height;

        this.colour = Color.WHITE;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public void draw(int index, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(colour);
        g2d.fillRect((index * width) + 200 + width, 600 - height, width, height);
    }

}
