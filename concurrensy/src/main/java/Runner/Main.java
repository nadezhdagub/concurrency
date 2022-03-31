package Runner;

class Runner1 {
    public void startRunning() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Runner1: " + i);
        }
    }
}

class Runner2 {
    public void startRunning() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Runner2: " + i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Runner1: " + i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Runner2: " + i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        System.out.println("Main exited");
    }
}
