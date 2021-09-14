package algorithm.visualiser.algorithms;

public class BubbleSort extends Algorithm {

    private int i;
    private int j;

    public BubbleSort(int delay) {
        super(delay);
        this.name = "Bubble Sort";
    }

    @Override
    public int[] getActiveIndexes() {
        return  new int[] {i, j, j + 1};
    }

    @Override
    public void run() {
        for (i = 0; i < array.size() - 1; i++) {
            for (j = 0; j < array.size()  - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    array.swap(j, j + 1, delay);
                }
            }
        }
    }

}
