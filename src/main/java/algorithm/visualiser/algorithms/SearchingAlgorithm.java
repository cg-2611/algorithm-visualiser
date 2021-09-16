package algorithm.visualiser.algorithms;

import algorithm.visualiser.util.Array;

public abstract class SearchingAlgorithm implements Algorithm {

    protected Array array;

    protected int delay;

    protected int target;
    protected int found;

    public SearchingAlgorithm() {
        this.delay = 250;

        this.target = 1;
        this.found = -1;
    }

    public int foundIndex() {
        return found;
    }

    @Override
    public void setArray(Array array) {
        this.array = array;
    }

    @Override
    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setTarget(int target) {
        this.target = target;
    }

}
