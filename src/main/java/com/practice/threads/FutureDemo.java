package com.practice.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
1. Future is a base interface and defines abstraction of an object which promises result to be available in future while FutureTask is an implementation of the Future interface.
2. Future is a parametric interface and type-safe written as Future<V>, where V denotes value.
3. Future provides get() method to get result, which is blocking method and blocks until result is available to Future.
4. Future interface also defines cancel() method to cancel task.
5. isDone() and isCancelled() method is used to query Future task states. isDone() returns true if task is completed and result is available to Future. If you call get() method, after isDone() returned true then it should return immediately. On the other hand, isCancelled() method returns true, if this task is cancelled before its completion.
6. Future has four sub interfaces, each with additional functionality e.g. Response, RunnableFuture, RunnableScheduledFuture and ScheduledFuture. RunnableFuture also implements Runnable and successful finish of run() method cause completion of this Future.
7. FutureTask and SwingWorker are two well known implementation of Future interface. FutureTask also implements RunnableFuture interface, which means this can be used as Runnable and can be submitted to ExecutorService for execution.
8. Though most of the time ExecutorService creates FutureTask for you, i.e. when you submit() Callable or Runnable object. You can also created it manually.
9. FutureTask is normally used to wrap Runnable or Callable object and submit them to ExecutorService for asynchronous execution

*/


public class FutureDemo {
    private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

    public static void main(String args[]) throws InterruptedException, ExecutionException {
        FactorialCalculator task = new FactorialCalculator(10);
        System.out.println("Submitting Task ...");
        Future future = threadpool.submit(task);
        System.out.println("Task is submitted");
        while (!future.isDone()) {
            System.out.println("Task is not completed yet....");
            Thread.sleep(1); //sleep for 1 millisecond before checking again

        }
        System.out.println("Task is completed, let's check result");
        long factorial = (Long)future.get();
        System.out.println("Factorial of 1000000 is : " + factorial);
        threadpool.shutdown();
    }
}
class FactorialCalculator implements Callable {
    private final int number;

    public FactorialCalculator(int number) {
        this.number = number;
    }

    public Long call() {
        long output = 0;
        try {
            output = factorial(number);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }

    private long factorial(int number) throws InterruptedException {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be greater than zero");
        }
        long result = 1;
        while (number > 0) {
            Thread.sleep(1); // adding delay for example
            result = result * number;
            number--;
        }
        return result;
    }
}
