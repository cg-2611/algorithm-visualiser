package algorithm.visualiser.entities;

import java.util.Random;

public class BarArray {

    private Bar[] bars;

    private int size;

    private int barWidth;
    private int barHeight;

    public BarArray(int size, int barWidth, int barHeight) {
        bars = new Bar[size];

        this.size = size;

        this.barWidth = barWidth;
        this.barHeight = barHeight;

        resetBars();
    }

    public Bar[] getBars() {
        return bars;
    }

    public int getSize() {
        return size;
    }

    public Bar getBar(int index) {
        return bars[index];
    }

    public void resetBars() {
        for (int i = 0; i < size; i++) {
            Bar bar = new Bar(i, barWidth, barHeight + (i * 20));
            bars[i] = bar;
        }
    }

}
