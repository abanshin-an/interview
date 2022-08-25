package ru.interview;

public class Exercise1 {
    /*
1. Реализовать программу, в которой два потока поочередно пишут ping и pong.
     */
    private static final Object mon = new Object();
    private static final String[] strings = new String[]{"Ping", "Pong"};
    private static volatile String current = strings[0];
    Exercise2 c= new Exercise2();
    public static void main(String[] args) {
        Exercise1 m = new Exercise1();
        Thread t1 = new Thread(() -> m.print(0));
        Thread t2 = new Thread(() -> m.print(1));
        t1.start();
        t2.start();
    }

    public void print(int index) {
        try {
            for (int i = 0; i < 100; i++) {
                synchronized (mon) {
                    while (!current.equals(strings[index])) {
                        mon.wait();
                    }
                    System.out.println(strings[index] + " " + c.increment());
                    current = strings[(index + 1) % 2];
                    mon.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }


}