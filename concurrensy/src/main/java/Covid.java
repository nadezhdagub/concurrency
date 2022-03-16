import java.util.Random;
import java.util.concurrent.Callable;

class Statistician implements Callable<Void> {

    private final int id;
    private final int count;
    private static Random random = new Random();

    Statistician(int id) {
        this.id = id;
        count = random.nextInt(1000) + 100;
    }
    
    @Override
    public Void call() throws Exception {
        return null;
    }
}

public class Covid {
    public static void main(String[] args) {

    }
}
