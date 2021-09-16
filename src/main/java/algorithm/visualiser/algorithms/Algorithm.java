package algorithm.visualiser.algorithms;

import algorithm.visualiser.util.Array;

public interface Algorithm {

    public String getName();

    public void setArray(Array array);

    public void setDelay(int delay);

    public int[] getActiveIndexes();

    public void run();

}
