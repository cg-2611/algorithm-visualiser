package algorithm.visualiser.algorithms.sorting;

import algorithm.visualiser.algorithms.SortingAlgorithm;

public class QuickSort extends SortingAlgorithm {

    private int pivot;
    private int index;

    public QuickSort() {
        super();
    }

    private int partition(int start, int end) throws InterruptedException {
        pivot = array.get(end);
        index = start - 1;

        for (int j = start; j < end; j++) {
            if (array.get(j) <= pivot) {
                index++;

                array.swap(index, j);
            }

            if (pivot > 0 && index > 0) {
                Thread.sleep(delay);
            }
        }

        array.swap(index + 1, end);

        return index + 1;
    }

    private void quickSort(int start, int end) throws InterruptedException {
        if (start < end) {
            int partitionIndex = partition(start, end);

            quickSort(start, partitionIndex - 1);
            quickSort(partitionIndex + 1, end);
        }
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {index, pivot};
    }

    @Override
    public void run() throws InterruptedException {
        quickSort(0, array.size() - 1);
    }

}
