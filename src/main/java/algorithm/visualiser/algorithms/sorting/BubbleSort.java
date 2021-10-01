package algorithm.visualiser.algorithms.sorting;

import algorithm.visualiser.algorithms.SortingAlgorithm;

public class BubbleSort extends SortingAlgorithm {

    private int i;

    public BubbleSort() {
        super();
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public int[] getActiveIndexes() {
        return  new int[] {i, i + 1};
    }

    @Override
    public void run() throws InterruptedException {
        setDelay(2500 / array.size());

        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (i = 0; i < array.size()  - 1; i++) {
                if (array.get(i) > array.get(i + 1)) {
                    array.swap(i, i + 1);
                    swapped = true;
                }

                Thread.sleep(delay);
            }
        }
    }

}
