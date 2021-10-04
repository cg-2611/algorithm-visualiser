package algorithm.visualiser.algorithms.sorting;

import algorithm.visualiser.algorithms.SortingAlgorithm;

public class QuickSort extends SortingAlgorithm {

    private int pivot;
    private int index;

    public QuickSort() {
        super();
    }

    private int partition(int low, int high) throws InterruptedException {
        pivot = array.get(high);
        index = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (array.get(j) <= pivot) {
                index++;
                array.swap(index, j);
            }

            if ((index >= 0 && index < array.size()) && (pivot >= 0 && pivot < array.size())) {
                Thread.sleep(delay);
            }
        }

        array.swap(index + 1, high);
        return index + 1;
    }

    private void quickSort(int low, int high) throws InterruptedException {
        if (low < high) {
            int partitionIndex = partition(low, high);

            quickSort(low, partitionIndex - 1);
            quickSort(partitionIndex + 1, high);
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
        setDelay(2500 / array.size());
        quickSort(0, array.size() - 1);
    }

}
