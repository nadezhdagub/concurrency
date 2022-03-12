package MySingleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum MySingleTon {
    INSTANCE;

    MySingleTon() {
        System.out.println("Constructor");
    }

    public String getData() {
        return "data";
    }

    public static String getVersion() {
        return "1";
    }
}

//class MySingleTon {
//    private MySingleTon() {
//
//    }
//
//    //public static final MySingleTon INSTANCE = new MySingleTon();
//
//    private static volatile MySingleTon instance;
//
//    public static MySingleTon getInstance() {
//        if (instance == null) {
//            synchronized (MySingleTon.class) {
//                if (instance == null) {
//                    instance = new MySingleTon();
//                }
//            }
//        }
//        return instance;
//    }
//}

public class Main {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
//        es.execute(() -> System.out.println(MySingleTon.getInstance()));
//        es.execute(() -> System.out.println(MySingleTon.getInstance()));
        es.execute(() -> System.out.println(MySingleTon.INSTANCE));
        es.execute(() -> System.out.println(MySingleTon.getVersion()));
    }
}
