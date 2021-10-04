package algorithm.visualiser.algorithms;

import algorithm.visualiser.util.Array;

public abstract class SearchingAlgorithm implements Algorithm {

    protected Array array;

    protected int delay;

    protected int target;
    protected int found;

    /**
     * Creates a default searching algorithm with a time delay of 250 ms and a target element of 1.
     */
    public SearchingAlgorithm() {
        this.delay = 250;

        this.target = 1;
        this.found = -1;
    }

    /**
     * Resets the index of the found element.
     */
    public void resetFoundIndex() {
        this.found = -1;
    }

    /**
     *
     * @return the index of the target if it is found.
     */
    public int getFoundIndex() {
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

    /**
     * Sets the target index of the algorithm.
     * @param target the value of the element to be found
     */
    public void setTarget(int target) {
        this.target = target;
    }

}
