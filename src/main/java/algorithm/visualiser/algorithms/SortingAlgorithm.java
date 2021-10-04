package algorithm.visualiser.algorithms;

import algorithm.visualiser.util.Array;

public abstract class SortingAlgorithm implements Algorithm {

    protected Array array;

    protected int delay;

    /**
     * Creates a default sorting algorithm with a time delay of 250 ms.
     */
    public SortingAlgorithm() {
        this.delay = 250;
    }

    @Override
    public void setArray(Array array) {
        this.array = array;
    }

    @Override
    public void setDelay(int delay) {
        this.delay = delay;
    }

}
