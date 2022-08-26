package ru.interview;

// 2. Реализовать потокобезопасный счетчик с помощью интерфейса Lock.

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise2{

    private int counter;
    private final Lock lock;

    public Exercise2(){
        this.counter = 0;
        this.lock = new ReentrantLock();
    }

    public int increment() {
        try {
            if(lock.tryLock(10, TimeUnit.SECONDS)){
                return counter++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }finally{
            lock.unlock();
        }
        return -1;
    }
}
