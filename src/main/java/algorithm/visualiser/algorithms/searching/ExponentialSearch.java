package algorithm.visualiser.algorithms.searching;

import algorithm.visualiser.algorithms.SearchingAlgorithm;

public class ExponentialSearch extends SearchingAlgorithm {

    private int bound;
    private int low;
    private int high;
    private int mid;

    @Override
    public String getName() {
        return "Exponential Search";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {bound, low, high, mid};
    }

    @Override
    public void run() throws InterruptedException {
        if (array.size() == 0) {
            found = -1;
            return;
        }

        bound = 1;

        while (bound < array.size() && array.get(bound) < target) {
            bound *= 2;
        }

        low = bound / 2;
        high = Math.min(bound + 1, array.size());

        binarySearch();
    }

    private void binarySearch() throws InterruptedException {
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

}