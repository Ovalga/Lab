package concurrent;

import org.example.functions.LinkedListTabulatedFunction;
import org.example.functions.TabulatedFunction;
import org.example.functions.UnitFunction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Runnable task = new MultiplyingTask(tabulatedFunction);
            executorService.execute(task);
        }

        countDownLatch.await();
        executorService.shutdown();

        System.out.println(tabulatedFunction);
    }
}
