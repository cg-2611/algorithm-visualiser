package algorithm.visualiser.algorithms.sorting;

import algorithm.visualiser.algorithms.SortingAlgorithm;

public class InsertionSort extends SortingAlgorithm {

    private int i;
    private int previous;

    public InsertionSort() {
        super();
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {previous, i};
    }

    @Override
    public void run() throws InterruptedException {
        setDelay((int) (6250 / array.size()));

        for (i = 1; i < array.size(); i++) {
            int elementToInsert = array.get(i);
            previous = i - 1;

            while ((previous >= 0) && (array.get(previous) > elementToInsert)) {
                array.swap(previous + 1, previous);
                previous--;
            }

            if (previous >= 0) {
                Thread.sleep(delay);
            }

            array.set(previous + 1, elementToInsert);
        }
    }

}
