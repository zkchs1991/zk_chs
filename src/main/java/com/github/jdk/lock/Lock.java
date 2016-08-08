package com.github.jdk.lock;

/**
 * Created by zk_chs on 16/8/8.
 * Java的synchronized块是可重入的
 * 该类为不可重入锁
 */
public class Lock {

    private boolean isLocked = false;

    public synchronized void lock()
            throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }


}
