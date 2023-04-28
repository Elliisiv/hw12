package Task_2;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz{
    private int n;
    private BlockingQueue<String> queue;

    public FizzBuzz(int n) {
        this.n = n;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void fizz() {
        synchronized (queue) {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 != 0)
                    queue.offer("fizz");

            }
        }
    }


    public void buzz() {
        synchronized (queue) {
            for (int i = 1; i <= n; i++) {
                if (i % 5 == 0 && i % 3 != 0)
                    queue.offer("buzz");
            }
        }
    }

    public void fizzbuzz() {
        synchronized (queue) {
            for (int i = 1; i <= n; i++) {
                if (i % 5 == 0 && i % 3 == 0)
                    queue.offer("fizzbuzz");
            }
        }
    }

    public synchronized void number() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0)
                queue.offer(Integer.toString(i));
        }
        while (true) {
                if (!queue.isEmpty()) {
                    System.out.println(queue.poll());
                }
            }
    }
}
