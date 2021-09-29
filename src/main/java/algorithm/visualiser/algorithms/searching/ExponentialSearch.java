package algorithm.visualiser.algorithms.searching;

import algorithm.visualiser.algorithms.SearchingAlgorithm;

public class ExponentialSearch extends SearchingAlgorithm {

    private int low;
    private int high;
    private int mid;

    public ExponentialSearch() {
        super();

        this.mid = -1;
    }

    @Override
    public String getName() {
        return "Exponential Search";
    }

    @Override
    public int[] getActiveIndexes() {
        if (mid != -1) {
            return new int[] {low, high, mid};
        } else {
            return new int[] {low, high};
        }
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

    @Override
    public void run() throws InterruptedException {
        setDelay(1000);

        if (array.size() == 0) {
            found = -1;
            return;
        }

        high = 1;

        while (true) {
            if (high > array.size()) {
                break;
            }

            if (array.get(high) > target) {
                break;
            }

            high *= 2;

            if (high < array.size()) {
                Thread.sleep(delay);
            }
        }

        low = high / 2;
        high = Math.min(high + 1, array.size());

        binarySearch();
    }

}