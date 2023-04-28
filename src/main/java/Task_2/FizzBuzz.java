package Task_2;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz {
    private int n;
    private BlockingQueue<String> queue;
    public FizzBuzz(int n) {
        this.n = n;
        this.queue = new LinkedBlockingQueue<>();
    }
    public void fizz() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0)
                queue.offer("fizz");
        }
    }
    public void buzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0)
                queue.offer("buzz");
        }
    }
    public void fizzbuzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 == 0)
                queue.offer("fizzbuzz");
        }
    }
    public synchronized void number() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                queue.offer(Integer.toString(i));

            }
        }
        int count = 0;
        while (count < n) {
            try {
                String result = queue.take(); // блокуємо доки є що виводити
                System.out.println(result);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);
        Thread threadA = new Thread(() -> fizzBuzz.fizz());
        Thread threadB = new Thread(() -> fizzBuzz.buzz());
        Thread threadC = new Thread(() -> fizzBuzz.fizzbuzz());
        Thread threadD = new Thread(() -> {
            fizzBuzz.number();
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

    }
}