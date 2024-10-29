public class Producer implements Runnable {
    private String s;
    private final Fifo fifo;
    private int count;
    private final long waitTime;

    public Producer(String s, Fifo fifo, long waitTime) {
        this.s = s;
        this.fifo = fifo;
        this.waitTime = waitTime;
    }

    void go() {
        while (true) {
            System.out.println("produced " + s + " " + count + " " + (System.currentTimeMillis() % 100000));
            fifo.put(s + " " + count++);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void run() {
        go();
    }
}
