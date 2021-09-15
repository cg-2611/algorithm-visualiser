package algorithm.visualiser.algorithms;

public class LinearSearch extends Algorithm {

    private int target;
    private int found;

    private int i;

    public LinearSearch(int delay) {
        super(delay);
        this.name = "Linear Search";

        this.found = -1;

        // temporary
        target = 7;
    }

    public int getFound() {
        return found;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @Override
    public int[] getActiveIndexes() {
        return new int[] {i};
    }

    @Override
    public void run() {
        for (i = 0; i < array.size(); i++) {
            if (array.get(i) == target) {
                found = i;
                break;
            }
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
