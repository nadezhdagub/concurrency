package ForkJoinPoolEx;

public class MaximumFindTask {

    private final long[] data;
    private final int lowIndex;
    private final int highIndex;

    public MaximumFindTask(long[] data, int lowIndex, int highIndex) {
        this.data = data;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    protected long computer() {
        if(highIndex - lowIndex < 10) {
            return sequetialMaxFind();
            
        }
    }

    private long sequetialMaxFind() {
    }

    public static void main(String[] args) {

    }
}
