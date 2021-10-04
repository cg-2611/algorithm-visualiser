package algorithm.visualiser.util;

import java.util.Random;

public class Array {

    private int[] array;

    /**
     * Creates a new Array object.
     * @param size the length of the array
     */
    public Array(int size) {
        array = new int[size];

        reset();
    }

    /**
     *
     * @return the length of the array
     */
    public int size() {
        return array.length;
    }

    /**
     *
     * @param index the index of the element to be fetched
     * @return the value of the element at the given index
     */
    public int get(int index) {
        return array[index];
    }

    /**
     *
     * @return the primitive array used to store the values
     */
    public int[] getArray() {
        return array;
    }

    /**
     * Re-populate the array with ascending values.
     */
    public void reset() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    /**
     * Re-populate the array with descending values.
     */
    public void reverse() {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
    }

    /**
     * Randomise the elements of the array.
     */
    public void shuffle() {
        Random random = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    /**
     * Swap two array elements.
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     */
    public void swap(int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * Set the value of an element in the array.
     * @param index the index of the element to be set
     * @param value the new value of the element at the provided index
     */
    public void set(int index, int value) {
        array[index] = value;
    }

}
