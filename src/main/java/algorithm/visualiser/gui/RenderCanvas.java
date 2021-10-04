package algorithm.visualiser.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import algorithm.visualiser.util.Array;

public class RenderCanvas extends Canvas {

    private static final Color[] ACTIVE_COLOURS = {Color.RED, Color.BLUE, Color.MAGENTA};

    private Array array;

    /**
     * Creates a new default RenderCanvas Object.
     */
    public RenderCanvas() {
        array = new Array(10);

        setBackground(Color.WHITE);
    }

    /**
     *
     * @return the array being rendered
     */
    public Array getArray() {
        return array;
    }

    /**
     * Resets the array to a new array.
     * @param length the length of the new array
     */
    public void updateArray(int length) {
        array = new Array(length);
    }

    /**
     * Render the array with a bar representing each element.
     * @param g the Graphics object being used to draw the bars
     */
    public void renderBars(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int i = array.size() - 1; i >= 0; i--) {
            renderBar(g, i);
        }
    }

     /**
      * Calculates the position, height and width of a bar and draws it to the canvas.
      * @param g the Graphics object being used to draw the bars
      * @param index the index of the bar in the array
      */
    public void renderBar(Graphics g, int index) {
        int barWidth = getWidth() / array.size();
        int barHeight = ((getHeight() - 5) / array.size()) * array.get(index);

        int x = getWidth() - (barWidth * (array.size() - index));
        int y = getHeight() - barHeight;

        g.fillRect(x, y, barWidth, barHeight);
    }

    /**
     * Renders a coloured bar.
     * @param g the Graphics object being used to draw the bars
     * @param index the index of the bar in the array
     * @param colourIndex the index used to chose the colour of the bar
     */
    public void renderActiveBar(Graphics g, int index, int colourIndex) {
        g.setColor(ACTIVE_COLOURS[colourIndex]);
        renderBar(g, index);
    }

    /**
     * Renders a green bar to show when a target is found when searching.
     * @param g the Graphics object being used to draw the bars
     * @param index the index of the bar in the array
     */
    public void renderTargetBar(Graphics g, int index) {
        g.setColor(Color.GREEN);
        renderBar(g, index);
    }

}
