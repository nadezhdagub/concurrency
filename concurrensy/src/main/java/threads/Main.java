package threads;

public class Main {
    public static void main(String[] args) {
        int activeThreadsCount = Thread.activeCount();
        System.out.println("Active threads count: ");

        Thread[] threadInfo = new Thread[activeThreadsCount];

        Thread.enumerate(threadInfo);
        for (Thread ti : threadInfo) {
            System.out.println("name" + ti.getName());
            System.out.println("demon:" + (ti.isDaemon() ? "yes" : "no"));;
        }
    }
}
