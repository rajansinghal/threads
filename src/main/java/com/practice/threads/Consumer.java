package com.practice.threads;

public class Consumer {

    public static void main(String[] args) {

        Producer b = new Producer();
        b.start();
        synchronized (b) {

            try {
                System.out.println( " Waiting for b to complete...");
                b.wait();

            } catch (InterruptedException e) {
                 // ThreadB (Producer) Thread A (Consumer)
             }
             System.out.println("Total is: " + b.total);
        }

    }

}

class Producer extends Thread {

    int total;
    public void run() {
        synchronized (this) {
             for (int i = 0; i <= 10 ;i++){
                 total += i;
            }
             notify();
         }
     }

}