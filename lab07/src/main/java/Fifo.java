import java.util.LinkedList;
import java.util.Queue;

public class Fifo {
    private final Queue<String> q = new LinkedList<>();

    public synchronized void put(String s) {
        System.out.println("Fifo.put, thread" + Thread.currentThread().threadId());
        q.add(s);
        notifyAll();
    }

    public synchronized String get() throws InterruptedException {
        System.out.println("Fifo.get, thread" + Thread.currentThread().threadId());
        while (true) {
            String s = q.poll();
            if (s != null) return s;
            wait();
        }
    }
}
