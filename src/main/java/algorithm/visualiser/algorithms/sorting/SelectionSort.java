package algorithm.visualiser.algorithms.sorting;

import algorithm.visualiser.algorithms.SortingAlgorithm;

public class SelectionSort extends SortingAlgorithm {

    private int i;
    private int j;

    public SelectionSort() {
        super();
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {i, j,};
    }

    @Override
    public void run() throws InterruptedException {
        for (i = 0; i < array.size() - 1; i++) {
            int smallestIndex = i;
            for (j = i + 1; j < array.size(); j++) {
                if (array.get(j) < array.get(smallestIndex)) {
                    smallestIndex = j;
                }

                Thread.sleep(delay);
            }

            array.swap(smallestIndex, i);
        }
    }

}
