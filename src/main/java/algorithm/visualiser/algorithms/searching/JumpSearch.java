package algorithm.visualiser.algorithms.searching;

import algorithm.visualiser.algorithms.SearchingAlgorithm;

public class JumpSearch extends SearchingAlgorithm {

    private double low;
    private double high;

    public JumpSearch() {
        super();
    }

    @Override
    public String getName() {
        return "Jump Search";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {(int) low, (int) high};
    }

    @Override
    public void run() throws InterruptedException {
        setDelay(500);

        double jump = Math.sqrt(array.size());

        low = 0;
        high = jump;

        while (array.get((int)high) < target) {
            Thread.sleep(delay);

            low = high;
            high += jump;

            if (low >= array.size()) {
                found = -1;
                return;
            }

            high = Math.min(high, array.size() - 1);
        }

        while (array.get((int)low) < target) {
            low++;

            if (low == Math.min(high, array.size())) {
                found = -1;
                return;
            }

            Thread.sleep(delay);
        }

        if (array.get((int)low) == target) {
            found = (int)low;
        } else {
            found = -1;
        }
    }

}
