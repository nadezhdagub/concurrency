package FutureEx;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Backup implements Runnable {

    @Override
    public void run() {
        System.out.println("Backing up me data");
    }
}

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleAtFixedRate(new Backup(), 3, 3, TimeUnit.SECONDS);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data ");
        String line = sc.nextLine();
        while(!line.isEmpty()) {
            System.out.println("Hello " + line);
            line = sc.nextLine();
        }
    }
}
