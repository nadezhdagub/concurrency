package ForkJoinPoolEx;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        long[] nums = initializeNums();
        ForkJoinPool fjp = ForkJoinPool.commonPool();
        MaximumFindTask findTask = new MaximumFindTask(nums, 0, nums.length);

        //SendRequestAction task = new SendRequestAction(1000);
        //fjp.invoke(task);
        long result = fjp.invoke(findTask);
        System.out.println("Result: " + result);
    }

    private static long[] initializeNums() {
        Random random = new Random();

        long[] nums = new long[300000000];

        for (int i = 0; i < 300000000; ++i) {
            nums[i] = random.nextInt(100);
        }
        return nums;
    }
}
