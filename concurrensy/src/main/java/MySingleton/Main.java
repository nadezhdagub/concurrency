package MySingleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MySingleTon {
    private MySingleTon() {

    }

    public static final MySingleTon INSTANCE = new MySingleTon();

//
//    private static MySingleTon instance;
//
//    static MySingleTon getInstance() {
//        if (instance == null) {
//            instance = new MySingleTon();
//        }
//        return instance;
//    }
}

public class Main {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(() -> System.out.println(MySingleTon.INSTANCE));
        es.execute(() -> System.out.println(MySingleTon.INSTANCE));
    }
}
