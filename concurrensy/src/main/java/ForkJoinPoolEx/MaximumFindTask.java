package ForkJoinPoolEx;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import static java.util.concurrent.ForkJoinTask.invokeAll;

public class MaximumFindTask extends RecursiveTask<Long> {

    private final long[] data;
    private final int lowIndex;
    private final int highIndex;

    public MaximumFindTask(long[] data, int lowIndex, int highIndex) {
        this.data = data;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Long compute() {
        if(highIndex - lowIndex < 10) {
            return sequetialMaxFind();
        } else {
            int middleIndex = (lowIndex + highIndex) / 2;

            MaximumFindTask leftSubtask = new MaximumFindTask(data, lowIndex, middleIndex);
            MaximumFindTask rightSubtask = new MaximumFindTask(data, middleIndex, highIndex);

            invokeAll(leftSubtask, rightSubtask);
            return Math.max(leftSubtask.join(), rightSubtask.join());
        }

    }

    private long sequetialMaxFind() {
        long max = data[0];

        for (int i = lowIndex; i < highIndex; ++i){
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }
}
