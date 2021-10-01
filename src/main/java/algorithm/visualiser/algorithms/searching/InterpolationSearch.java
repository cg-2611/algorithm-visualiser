package algorithm.visualiser.algorithms.searching;

import algorithm.visualiser.algorithms.SearchingAlgorithm;

public class InterpolationSearch extends SearchingAlgorithm {

    private int low;
    private int high;
    private int mid;

    public InterpolationSearch() {
        super();
        reset();
    }

    private void reset() {
        this.mid = -1;
    }

    @Override
    public String getName() {
        return "Interpolation Search";
    }

    @Override
    public int[] getActiveIndexes() {
        return mid != -1 ? new int[] {low, high, mid} : new int[] {low, high};
    }

    @Override
    public void run() throws InterruptedException {
        reset();
        setDelay(2500);

        low = 0;
        high = array.size() - 1;

        while ((array.get(high) != array.get(low)) && (target >= array.get(low)) && (target <= array.get(high))) {

            mid = low + ((target - array.get(low)) * (high - low)) / (array.get(high) - array.get(low));

            Thread.sleep(delay);

            if (target > array.get(mid)) {
                low = mid + 1;
            } else if (target < array.get(mid)) {
                high = mid - 1;
            } else {
                found = mid;
                return;
            }
        }

        if (target == array.get(low)) {
            found = low;
        } else {
            found = -1;
        }
    }

}
