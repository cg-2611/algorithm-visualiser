package algorithm.visualiser.algorithms.searching;

import algorithm.visualiser.algorithms.SearchingAlgorithm;

public class LinearSearch extends SearchingAlgorithm {

    private int i;

    public LinearSearch() {
        super();
    }

    @Override
    public String getName() {
        return "Linear Search";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {i};
    }

    @Override
    public void run() throws InterruptedException {
        for (i = 0; i < array.size(); i++) {
            if (array.get(i) == target) {
                found = i;
                break;
            }

            Thread.sleep(delay);
        }
    }

}