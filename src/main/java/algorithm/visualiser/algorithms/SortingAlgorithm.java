package algorithm.visualiser.algorithms;

import algorithm.visualiser.util.Array;

public abstract class SortingAlgorithm implements Algorithm {

    protected Array array;

    protected int delay;

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
