package com.practice.threads;

public class PrintEvenOddTester {

    public static void main(String... args) {
        Printer print = new Printer();
        Thread odd = new Thread(new TaskEvenOdd(print, 1));
        Thread even = new Thread(new TaskEvenOdd(print, 2));
        odd.start();
        even.start();
    }

}

class TaskEvenOdd implements Runnable {

    private int threadId;
    private Printer print;
    private  int number;

    TaskEvenOdd(Printer print, int max) {
        this.print = print;
        this.threadId = max;
        this.number = threadId;
    }

    public void run() {

        while (number <= 10) {

            if (threadId == 1) {
               print.printOdd(number);
            } else {
                print.printEven(number);
            }
            number += 2;
        }

    }

}

class Printer {

    boolean isOdd = true;
    boolean isEven = false;

    synchronized void printEven(int number) {

        while (isEven == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("exception even ");

                e.printStackTrace();
            }
        }
        System.out.println("Even:" + number);
        isOdd = true;
        isEven = false;
        notifyAll();
    }

    synchronized void printOdd(int number) {
        while (isOdd == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("exception odd ");
                e.printStackTrace();
            }
        }
        System.out.println("Odd:" + number);
        isOdd = false;
        isEven = true;
        notifyAll();
    }

}