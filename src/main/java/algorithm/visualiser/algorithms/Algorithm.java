package algorithm.visualiser.algorithms;

public abstract class Algorithm {

    protected String name;
    protected boolean algorithmComplete;

    protected int[] array;

    public Algorithm() {
        this.algorithmComplete = false;
    }

    public String getName() {
        return name;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public boolean isAlgorithmComplete() {
        return algorithmComplete;
    }

    public abstract void run();

}
