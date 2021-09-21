package algorithm.visualiser.algorithms.searching;

import algorithm.visualiser.algorithms.SearchingAlgorithm;

public class InterpolationSearch extends SearchingAlgorithm {

    private int low;
    private int high;
    private int mid;

    public InterpolationSearch() {
        super();
    }

    @Override
    public String getName() {
        return "Interpolation Search";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {low, high, mid};
    }

    @Override
    public void run() {
        low = 0;
        high = array.size() - 1;

        while ((array.get(high) != array.get(low)) && (target >= array.get(low)) && (target <= array.get(high))) {

            mid = low + ((target - array.get(low)) * (high - low)) / (array.get(high) - array.get(low));

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
