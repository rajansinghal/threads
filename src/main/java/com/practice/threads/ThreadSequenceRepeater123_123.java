package com.practice.threads;

public class ThreadSequenceRepeater123_123 {

    public static void main(String... args) {
        SequenceRepeatPrinter147 print = new SequenceRepeatPrinter147();
        Thread t1 = new Thread(new TaskSequenceRepeater147(print,  1));
        Thread t2 = new Thread(new TaskSequenceRepeater147(print, 2));
        Thread t3 = new Thread(new TaskSequenceRepeater147(print, 3));
        t1.start();
        t2.start();
        t3.start();
    }

}

class TaskSequenceRepeater implements Runnable {

    private int threardid;
    private SequenceRepeatPrinter147 print;

    TaskSequenceRepeater(SequenceRepeatPrinter147 print, int threardid) {
        this.print = print;
        this.threardid = threardid;
    }

    public void run() {

        while (true) {
            if (1 == threardid) {
                print.printFirst(1);
            } else if(2 == threardid ) {
               print.printSecond(2);

            } else if (3 == threardid){
                print.printThird(3);
            }

        }

    }

}

class SequenceRepeatPrinter {

    boolean one = true;
    boolean two = false;
    boolean three = false;

    synchronized void printFirst(int number) {

        while (!one) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("exception even ");

                e.printStackTrace();
            }
        }
        System.out.println("T1:" + number);
         one = false;
         two = true;
         three = false;
        notifyAll();
    }

    synchronized void printSecond(int number) {
        while (!two) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("exception odd ");
                e.printStackTrace();
            }
        }
        System.out.println("T2:" + number);
         one = false;
         two = false;
         three = true;
        notifyAll();
    }

    synchronized void printThird(int number) {
        while (!three) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("exception odd ");
                e.printStackTrace();
            }
        }
        System.out.println("T3:" + number);
         one = true;
         two = false;
         three = false;
        notifyAll();
    }

}