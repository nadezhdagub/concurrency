package threads;

class MyTaskSleepingNoInterrupt implements Runnable {

    @Override
    public void run() {
        while (true) {
            doSomething();
        }
    }

    private void doSomething() {
        System.out.println("Do something");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyTaskSleepingInterrupt implements Runnable {

    @Override
    public void run() {
        boolean res;
        do {
            res = doSomething();
        } while (res);
        System.out.println("Thread exist");
    }

    private boolean doSomething() {
        boolean res = true;
        System.out.println("Do something");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            res = false;
        }
        return res;
    }
}

public class InteraptSleepingThread {
    public static void main(String[] args) throws InterruptedException {
        //noInterruptionSleep();
        Thread t = new Thread(new MyTaskSleepingInterrupt());
        t.start();
        Thread.sleep(2000);
        System.out.println("Interrupting");
        t.interrupt();
        System.out.println("Main thread exist");
    }

    private static void noInterruptionSleep() throws InterruptedException {
        Thread t = new Thread(new MyTaskSleepingNoInterrupt());
        t.start();
        Thread.sleep(2000);
        System.out.println("Interrupting");
        t.interrupt();
        System.out.println("Main thread exist");
    }
}
