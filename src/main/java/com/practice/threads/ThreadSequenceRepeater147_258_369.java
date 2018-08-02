package com.practice.threads;

public class ThreadSequenceRepeater147_258_369 {

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

class TaskSequenceRepeater147 implements Runnable {

    private int threardid;
    private int number ;
    private SequenceRepeatPrinter147 print;

    TaskSequenceRepeater147(SequenceRepeatPrinter147 print, int threardid ) {
        this.print = print;
        this.threardid = threardid;
        this.number = threardid;
    }

    public void run() {

        while (number <= 10) {
            if (1 == threardid) {
                print.printFirst(number);
            } else if(2 == threardid ) {
                print.printSecond(number);

            } else if (3 == threardid){
                print.printThird(number);
            }
            number = number +3;
        }

    }

}

class SequenceRepeatPrinter147 {

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