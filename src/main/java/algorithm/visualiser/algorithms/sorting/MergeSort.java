package algorithm.visualiser.algorithms.sorting;

import algorithm.visualiser.algorithms.SortingAlgorithm;

public class MergeSort extends SortingAlgorithm {

    private int iArray;
    private int jArray;

    public MergeSort() {
        super();
    }

    private int[] getSubArray(int start, int length) {
        int[] subArray = new int[length];

        for (int i = 0; i < length; i++) {
            subArray[i] = array.get(start + i);
        }

        return subArray;
    }

    private void merge(int left, int mid, int right) throws InterruptedException {
        int leftLength = mid - left + 1;
        int rightLength = right - mid;

        int leftArray[] = getSubArray(left, leftLength);
        int rightArray[] = getSubArray(mid + 1, rightLength);

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftLength && j < rightLength) {
            if (leftArray[i] <= rightArray[j]) {
                array.set(k, leftArray[i]);
                i++;

                iArray = i + left;
                Thread.sleep(delay);
            } else {
                array.set(k, rightArray[j]);
                j++;

                jArray = j + mid;
                Thread.sleep(delay);
            }
            k++;
        }

        while (i < leftLength) {
            array.set(k, leftArray[i]);
            i++;
            k++;

            iArray = i + left;
            Thread.sleep(delay);
        }

        while (j < rightLength) {
            array.set(k, rightArray[j]);
            j++;
            k++;

            jArray = j + mid;
            Thread.sleep(delay);
        }
    }

    private void mergeSort(int left, int right) throws InterruptedException {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {iArray, jArray};
    }

    @Override
    public void run() throws InterruptedException {
        mergeSort(0, array.size() - 1);
    }

}
