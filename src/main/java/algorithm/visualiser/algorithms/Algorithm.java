package algorithm.visualiser.algorithms;

import algorithm.visualiser.util.Array;

public abstract class Algorithm {

    protected String name;
    protected int delay;

    protected Array array;

    public Algorithm(int delay) {
        this.delay = delay;
    }

    public String getName() {
        return name;
    }

    public void setArray(Array array) {
        this.array = array;
    }

    public abstract int[] getActiveIndexes();

    public abstract void run();

}
