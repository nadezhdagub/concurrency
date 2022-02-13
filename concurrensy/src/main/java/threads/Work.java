package threads;

class Spaceship implements Runnable {

    private final String name;
    private final boolean isSuccessfull;
    private final int count;

    Spaceship(String name, boolean isSuccessfull, int count) {
        this.name = name;
        this.isSuccessfull = isSuccessfull;
        this.count = count;
    }

    String getName() {
        return name;
    }



    @Override
    public void run() {
        System.out.println("Launching spaceship " + name +" in thread "
                + Thread.currentThread().getName());
        for (int i = 10; i >= 1; --i) {
            System.out.println(name + i + Thread.currentThread().getName());
            if ((i == 5) && !isSuccessfull) {
                throw new RuntimeException("Spaceship " + name + " crashed");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" spaceship launching");
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("My code executing in a thread");
    }
}

public class Work {
    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("some spaceship has crashed! Starting again");
                Spaceship spaceship = new Spaceship("Nostromo", true, 5);
                new Thread(spaceship).start();
            }
        });

        System.out.println("Main thread started");
        Spaceship spaceship1 = new Spaceship("Apollo", true, 5);
        Spaceship spaceship2 = new Spaceship("Nostromo", false, 10);
        Spaceship spaceship3 = new Spaceship("Soyuz", true, 7);
        Thread t1 = new Thread(spaceship1);
        t1.setName(spaceship1.getName());
        Thread t2 = new Thread(spaceship2);
        t2.setName(spaceship2.getName());
        Thread t3 = new Thread(spaceship3);
        t3.setName(spaceship3.getName());
        t1.start();
        t1.join();
        t2.start();
        t2.start();
        t3.start();
        System.out.println("Main thread ended");
//        Runnable r = new MyRunnable();
//        Thread t = new Thread(r);
//        t.start();
    }
}
