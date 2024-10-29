public class Consumer extends Thread {
    private Fifo f;
    private String s;
    private int n;

    public Consumer(Fifo f, String s, int n) {
        this.f = f;
        this.s = s;
        this.n = n;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String read = f.get();
                System.out.println("consumed " + s + " " + read + " " + System.currentTimeMillis() % 100000);
                sleep(n);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
