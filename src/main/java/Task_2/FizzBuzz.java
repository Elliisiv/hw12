package Task_2;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {
    private static final AtomicInteger CURRENT_NUMBER = new AtomicInteger(1);
    private BlockingQueue<String> queue;

    public FizzBuzz() {
        this.queue = new LinkedBlockingQueue<>();
    }

    public void fizz() throws InterruptedException {
        while (true) {
            synchronized (queue) {
                if (CURRENT_NUMBER.get() % 3 == 0 && CURRENT_NUMBER.get() % 5 != 0) {
                    queue.add("fizz");
                    CURRENT_NUMBER.incrementAndGet();
                    queue.notifyAll();
                } else {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Thread.sleep(1000);
        }
    }

    public void buzz() throws InterruptedException {
        while (true) {
            synchronized (queue) {
                if (CURRENT_NUMBER.get() % 5 == 0 && CURRENT_NUMBER.get() % 3 != 0) {
                    queue.add("buzz");
                    CURRENT_NUMBER.incrementAndGet();
                    queue.notifyAll();
                } else {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Thread.sleep(1000);
        }
    }

    public void fizzbuzz() throws InterruptedException {
        while (true) {
            synchronized (queue) {
                if (CURRENT_NUMBER.get() % 5 == 0 && CURRENT_NUMBER.get() % 3 == 0) {
                    queue.add("fizzbuzz");
                    CURRENT_NUMBER.incrementAndGet();
                    queue.notifyAll();
                } else {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Thread.sleep(1000);
        }
    }

    public void number() throws InterruptedException {
        while (true) {
            synchronized (queue) {
                if (CURRENT_NUMBER.get() % 5 != 0 && CURRENT_NUMBER.get() % 3 != 0) {
                    queue.add(Integer.toString(CURRENT_NUMBER.get()));
                    CURRENT_NUMBER.incrementAndGet();
                    queue.notifyAll();
                } else {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Thread.sleep(1000);
        }
    }

    public void printQueue() {
        while (true) {
            synchronized (queue) {
                while (!queue.isEmpty()) {
                    System.out.print(queue.poll() + ", ");
                }
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
