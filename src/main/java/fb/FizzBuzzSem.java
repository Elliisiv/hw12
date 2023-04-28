package fb;
import java.util.concurrent.*;

public class FizzBuzzSem {
    int n;

    private final Semaphore semFizz ,semBuzz ,semFizzBuzz, semNumber;

    public FizzBuzzSem(int n) {
        this.n = n;

        semFizz = new Semaphore(0);
        semBuzz = new Semaphore(0);
        semFizzBuzz = new Semaphore(0);
        semNumber = new Semaphore(1);
    }

    public void fizz() throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                semFizz.acquire();
                System.out.print ("fizz ");
                semNumber.release();
            }
        }
    }
    public void buzz() throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                semBuzz.acquire();
                System.out.print ("buzz ");
                semNumber.release();
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            semFizzBuzz.acquire();
            System.out.print ("fizzbuzz");
            semNumber.release();
        }
    }

    public void number() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semNumber.acquire();
            if((i % 3 == 0)&& (i % 5 == 0)){
                semFizzBuzz.release();
            }
            else if(i % 5 == 0){
                semBuzz.release();
            }
            else if(i % 3 == 0){
                semFizz.release();
            }
            else{
                System.out.print(i + " ");
                semNumber.release();
            }
        }
    }
}

class Number{
    public static void main(String[] args) {
        FizzBuzzSem fizzBuzz = new FizzBuzzSem(15);
        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}