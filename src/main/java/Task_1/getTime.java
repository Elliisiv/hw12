package Task_1;

import java.time.LocalDateTime;

public class getTime {
    public static void main(String[] args) {
        LocalDateTime startTime = LocalDateTime.now();
        Thread timer = new Thread(() -> {
            while (true) {
                LocalDateTime currentTime = LocalDateTime.now();
                long seconds = startTime.until(currentTime, java.time.temporal.ChronoUnit.SECONDS);
                if(seconds==5){
                    System.out.print("");
                }else {
                    System.out.println("З запуску програми минуло " + seconds +" секунд");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread message = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println("Минуло 5 секунд");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        timer.start();
        message.start();
    }
}