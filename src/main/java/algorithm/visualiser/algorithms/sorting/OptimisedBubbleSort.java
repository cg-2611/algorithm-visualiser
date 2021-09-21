package algorithm.visualiser.algorithms.sorting;

import algorithm.visualiser.algorithms.SortingAlgorithm;

public class OptimisedBubbleSort extends SortingAlgorithm {

    private int i;
    private int j;

    public OptimisedBubbleSort() {
        super();
    }

    @Override
    public String getName() {
        return "Optimised Bubble Sort";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {j, j + 1};
    }

    @Override
    public void run() throws InterruptedException {
        boolean swapped = true;
        i = 0;

        while ((swapped == true) && (i < array.size())) {
            swapped = false;
            for (j = 0; j < array.size() - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    array.swap(j, j + 1);
                    swapped = true;
                }

                Thread.sleep(delay);
            }
            i++;
        }
    }

}
