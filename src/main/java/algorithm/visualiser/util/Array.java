package algorithm.visualiser.util;

import java.util.Random;

public class Array {

    private int[] array;

    public Array(int size) {
        array = new int[size];

        reset();
    }

    public int size() {
        return array.length;
    }

    public int get(int index) {
        return array[index];
    }

    public int[] getArray() {
        return array;
    }

    public void reset() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    public void reverse() {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
    }

    public void shuffle() {
        Random random = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public void swap(int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public void set(int index, int value) {
        array[index] = value;
    }

}
