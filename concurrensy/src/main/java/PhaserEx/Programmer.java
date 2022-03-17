package PhaserEx;

public class Programmer implements Runnable{

    private final String name;
    private final Project project;

    public Programmer(String name, Project project) {
        this.name = name;
        this.project = project;
        project.phaser.register();
    }

    @Override
    public void run() {
        project.phaser.arriveAndAwaitAdvance();
        System.out.println(name + " arrived");
        doWork();
        project.phaser.arriveAndAwaitAdvance();
        System.out.println(name + " deregistered");
    }

    private void doWork() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void log(String s) {
        System.out.println(s);
    }
}
