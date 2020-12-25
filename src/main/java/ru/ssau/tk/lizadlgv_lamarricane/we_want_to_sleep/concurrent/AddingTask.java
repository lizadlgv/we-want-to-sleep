package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.concurrent;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;

public class AddingTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;
    private Runnable postRunAction;

    public AddingTask(TabulatedFunction func) {
        this.tabulatedFunction = func;
    }

    public AddingTask(TabulatedFunction func, Runnable postRunAction) {
        this.tabulatedFunction = func;
        this.postRunAction = postRunAction;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            x = tabulatedFunction.getX(i);
            synchronized (tabulatedFunction) {
                y = tabulatedFunction.getY(i);
                System.out.printf("%s, i = %d, x = %f, old y = %f \n", Thread.currentThread().getName(), i, x, y);
                tabulatedFunction.setY(i, y + 3);
                y = tabulatedFunction.getY(i);
            }
            System.out.printf("%s, i = %d, x = %f, new y = %f \n", Thread.currentThread().getName(), i, x, y);
        }
        postRunAction.run();
    }
}
