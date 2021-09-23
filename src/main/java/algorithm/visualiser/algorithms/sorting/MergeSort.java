package algorithm.visualiser.algorithms.sorting;

import algorithm.visualiser.algorithms.SortingAlgorithm;

public class MergeSort extends SortingAlgorithm {

    private int i = 0;
    private int j = 0;
    private int k = 0;

    public MergeSort() {
        super();
    }

    private void merge(int[] array, int[] left, int[] right, int leftIndex, int rightIndex) throws InterruptedException {
        i = 0;
        j = 0;
        k = 0;

        while (i < leftIndex && j < rightIndex) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < leftIndex) {
            array[k++] = left[i++];
        }

        while (j < rightIndex) {
            array[k++] = right[j++];
        }
    }

    private void mergeSort(int[] array, int length) throws InterruptedException {
        if (length < 2) {
            return;
        }

        int mid = length / 2;
        int[] left = new int[mid];
        int[] right = new int[length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < length; i++) {
            right[i - mid] = array[i];
        }

        mergeSort(left, mid);
        mergeSort(right, length - mid);

        merge(array, left, right, mid, length - mid);
    }


    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {i, j, k};
    }

    @Override
    public void run() throws InterruptedException {
        mergeSort(array.getArray(), array.size());
    }

}
