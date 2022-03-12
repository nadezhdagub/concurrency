package SynchronizeEx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class Actor implements Runnable{

    static ExecutorService es = Executors.newCachedThreadPool();

    final static Object scene = new Object();

    private final String name;

    Actor(String name) {
        this.name = name;
    }

    void play() {
        es.execute(this);
    }

    @Override
    public void run() {
        doPlay();
    }

    abstract protected void doPlay();

    protected void say(String text) {
        System.out.println(text);
    }
}

class Romeo extends Actor {

    private boolean isSaid;

    Romeo() {
        super("Romeo");
    }

    public boolean isSaid() {
        return isSaid;
    }

    @Override
    protected void doPlay() {
        synchronized (scene) {
            say("I love you");
            isSaid = true;
            scene.notify();
        }
    }
}

class Julliette extends Actor {
    private final Romeo romeo;

    Julliette(Romeo romeo) {
        super("Julliette");
        this.romeo = romeo;
    }

    @Override
    protected void doPlay() {
        synchronized (scene) {
            if(!romeo.isSaid()) {
                say("Waiting for Romeo");
                try {
                    scene.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        say("I love you, too");
    }
}

public class Shecspir {
    public static void main(String[] args) throws InterruptedException {
        Romeo romeo = new Romeo();
        Julliette julliette = new Julliette(romeo);
        romeo.play();
        Thread.sleep(1000);
        julliette.play();
        Actor.es.shutdown();
    }
}
