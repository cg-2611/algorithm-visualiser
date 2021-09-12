package algorithm.visualiser.algorithms;

public class BubbleSort extends Algorithm {

    public BubbleSort() {
        this.name = "Bubble Sort";
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length  - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        algorithmComplete = true;
    }

}
