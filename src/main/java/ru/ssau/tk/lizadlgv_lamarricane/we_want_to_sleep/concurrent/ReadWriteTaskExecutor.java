package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.concurrent;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.ZeroFunction;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        List<Thread> list = new ArrayList<>();

        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new ReadWriteTask(tabulatedFunction));
            list.add(thread);
        }
        for (Thread thread : list) {
            thread.start();
        }

        countDownLatch.await();
        System.out.println(tabulatedFunction.toString());
    }
}
