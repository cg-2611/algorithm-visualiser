package algorithm.visualiser.algorithms;

import algorithm.visualiser.util.Array;

public interface Algorithm {

    /**
     *
     * @return the name of the algorithm
     */
    public String getName();

    /**
     * Set the array for the algorithm to be performed on.
     * @param array the array the algorithm is performed on
     */
    public void setArray(Array array);

    /**
     * Set the time delay between algorithm steps.
     * @param delay the time delay to be used.
     */
    public void setDelay(int delay);

    /**
     * Get the indexes of the array elements that are currently being considered by the algorithm.
     * @return an array of the active indexes being used by the algorithm
     */
    public int[] getActiveIndexes();

    /**
     * Execute the algorithm.
     * @throws InterruptedException
     */
    public void run() throws InterruptedException;

}
