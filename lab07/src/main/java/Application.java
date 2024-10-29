import java.util.List;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Fifo fifo = new Fifo();
        for (int i = 0; i < 3; i++) {
            new Thread(new Producer("prod" + i, fifo, 1000)).start();
        }
        for (int i = 0; i < 4; i++) {
            new Consumer(fifo,  "cons" + i, 1000).start();
        }
    }
}
