package algorithm.visualiser.algorithms.sorting;

import algorithm.visualiser.algorithms.SortingAlgorithm;

public class BubbleSort extends SortingAlgorithm {

    private int i;
    private int j;

    public BubbleSort() {
        super();
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public int[] getActiveIndexes() {
        return  new int[] {j, j + 1};
    }

    @Override
    public void run() {
        for (i = 0; i < array.size() - 1; i++) {
            for (j = 0; j < array.size()  - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    array.swap(j, j + 1);
                }

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
