package algorithm.visualiser.algorithms.searching;

import algorithm.visualiser.algorithms.SearchingAlgorithm;

public class ExponentialSearch extends SearchingAlgorithm {

    private int low;
    private int high;
    private int mid;

    public ExponentialSearch() {
        super();
        reset();
    }

    private void reset() {
        this.mid = -1;
    }

    @Override
    public String getName() {
        return "Exponential Search";
    }

    @Override
    public int[] getActiveIndexes() {
        return mid != -1 ? new int[] {low, high, mid} : new int[] {low, high};
    }

    private void binarySearch() throws InterruptedException {
        mid = low  + ((high - low) / 2);

        Thread.sleep(delay);

        if (high < low) {
            found = -1;
            return;
        }

        if (target == array.get(mid)) {
            found = mid;
        } else if (target < array.get(mid)) {
            high = mid - 1;
            binarySearch();
        } else {
            low = mid + 1;
            binarySearch();
        }
    }

    @Override
    public void run() throws InterruptedException {
        reset();
        setDelay(500);

        if (array.size() == 0) {
            found = -1;
            return;
        }

        low = 0;
        high = 1;

        while (high < array.size() && array.get(high) < target) {
            Thread.sleep(delay);
            high *= 2;
        }

        low = high / 2;
        high = Math.min(high, array.size() - 1);

        setDelay(1000);
        binarySearch();
    }

}