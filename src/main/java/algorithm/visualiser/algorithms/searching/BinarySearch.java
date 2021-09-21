package algorithm.visualiser.algorithms.searching;

import algorithm.visualiser.algorithms.SearchingAlgorithm;

public class BinarySearch extends SearchingAlgorithm {

    private int low;
    private int high;
    private int mid;

    public BinarySearch() {
        super();
        reset();
    }

    private void reset() {
        this.low = -1;
        this.high = -1;
    }

    @Override
    public String getName() {
        return "Binary Search";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {low, high, mid};
    }

    private void binarySearch() throws InterruptedException {
        if (low == -1) {
            low = 0;
        }

        if (high == -1) {
            high = array.size() - 1;
        }

        mid = low  + ((high - low) / 2);

        if (high < low) {
            found = -1;
            return;
        }

        if (target == array.get(mid)) {
            found = mid;
        } else if (target < array.get(mid)) {
            high = mid - 1;

            Thread.sleep(delay);

            binarySearch();
        } else {
            low = mid + 1;

            Thread.sleep(delay);

            binarySearch();
        }
    }

    @Override
    public void run() throws InterruptedException {
        reset();
        binarySearch();
    }

}
