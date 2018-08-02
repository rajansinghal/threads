package com.practice.threads;

public class SequenceDisplay {

    public static void main(String[] args) {

        Thread t1 = new Thread(new SequenceDisplayImpl(1));
        Thread t2 = new Thread(new SequenceDisplayImpl(2));
        Thread t3 = new Thread(new SequenceDisplayImpl(3));
        t1.start();
        t2.start();
        t3.start();
    }
}

class SequenceDisplayImpl implements Runnable {

     boolean one = true;
     boolean two = false;
     boolean three = false;


    int threadId;

    SequenceDisplayImpl(int threadId) {
        this.threadId = threadId;
    }

    public void run() {

        try {
            while (true) {
                Thread.sleep(500);
                synchronized (this) {
                    if (1 == threadId) {
                        if (!one) {
                            this.wait();
                        } else {
                            System.out.print(threadId + " ");
                            one = false;
                            two = true;
                            three = false;
                            this.notifyAll();
                        }
                    }
                    else  if (2 == threadId) {
                        if (!two) {
                            this.wait();
                        } else {
                            System.out.print(threadId + " ");
                            one = false;
                            two = false;
                            three = true;
                            this.notifyAll();
                        }
                    }
                    else if (3 == threadId) {
                        if (!three) {
                            this.wait();
                        } else {
                            System.out.print(threadId + " ");
                            one = true;
                            two = false;
                            three = false;
                            this.notifyAll();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}