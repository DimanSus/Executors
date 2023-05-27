import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(11);
        CountDownLatch countDownLatch1 = new CountDownLatch(11);
        CountDownLatch countDownLatch2 = new CountDownLatch(11);
        CountDownLatch countDownLatch3 = new CountDownLatch(11);
        CountDownLatch countDownLatch4 = new CountDownLatch(11);
        CountDownLatch countDownLatch5 = new CountDownLatch(11);
        CountDownLatch countDownLatch6 = new CountDownLatch(11);
        CountDownLatch countDownLatch7 = new CountDownLatch(11);
        CountDownLatch countDownLatch8 = new CountDownLatch(11);
        CountDownLatch countDownLatch9 = new CountDownLatch(11);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        System.out.println("Запуск потоков!");

        executorService.execute(new MyThread(countDownLatch, "A"));
        executorService.execute(new MyThread(countDownLatch1, "B"));
        executorService.execute(new MyThread(countDownLatch2, "C"));
        executorService.execute(new MyThread(countDownLatch3, "D"));
        executorService.execute(new MyThread(countDownLatch4, "E"));
        executorService.execute(new MyThread(countDownLatch5, "F"));
        executorService.execute(new MyThread(countDownLatch6, "G"));
        executorService.execute(new MyThread(countDownLatch7, "H"));
        executorService.execute(new MyThread(countDownLatch8, "I"));
        executorService.execute(new MyThread(countDownLatch9, "J"));

        try{
            countDownLatch.await();
            countDownLatch1.await();
            countDownLatch2.await();
            countDownLatch3.await();
            countDownLatch4.await();
            countDownLatch5.await();
            countDownLatch6.await();
            countDownLatch7.await();
            countDownLatch8.await();
            countDownLatch9.await();
        } catch (InterruptedException exc){
            System.out.println(exc);
        }

        executorService.shutdown();
        System.out.println("Завершение потоков");
    }
}

class MyThread implements Runnable{
    String name;
    CountDownLatch latch;

    public MyThread( CountDownLatch latch, String name) {
        this.name = name;
        this.latch = latch;
        new Thread(this);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(name + ": " + i);
            latch.countDown();
        }
    }
}