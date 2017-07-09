package ru.kovladimir.threads.lock;

/**
 * Basic Lock.
 */
public class BasicLock {

    /**
     * Inner lock to synchronization.
     */
    private final Object innerLock = new Object();

    /**
     * Boolean flag is lock or not.
     */
    private boolean locked = false;

    /**
     * Lock.
     */
    public void lock() {
        synchronized (innerLock) {
            while (locked) {
                try {
                    innerLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            locked = true;
        }
    }

    /**
     * Unlock.
     */
    public void unlock() {
        synchronized (innerLock) {
            locked = false;
            innerLock.notifyAll();
        }
    }

}
