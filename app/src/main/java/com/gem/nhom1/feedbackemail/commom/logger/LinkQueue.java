package com.gem.nhom1.feedbackemail.commom.logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vanhop on 2/3/16.
 */
public class LinkQueue<T> {
    private int maxQueueSize = 10000;
    private ArrayBlockingQueue<T> queueData = new ArrayBlockingQueue<T>(maxQueueSize);
    private AtomicInteger miQueueSize = new AtomicInteger();
    private Object mutex;
    private String mstrQueueName = "Link Queue";

    private boolean mbLocked = false;

    public LinkQueue() {
        mutex = this;
    }

    public LinkQueue(int maxSize) {
        maxQueueSize = maxSize;
        mutex = this;
    }

    public LinkQueue(int maxSize, String strQueueName) {
        mstrQueueName = strQueueName;
        maxQueueSize = maxSize;
        mutex = this;
    }

    public void enqueueNotify(T objMsg) {
        if (!mbLocked) {
            if ((maxQueueSize > 0) && (miQueueSize.intValue() >= maxQueueSize)) {
                System.out.println("Queue is full. Element not added. Queue name (" + mstrQueueName + ")");
                return;
            }

            synchronized (queueData) {
                queueData.add(objMsg);
            }
            miQueueSize.incrementAndGet();

            synchronized (mutex) {
                mutex.notify();
            }
        }
    }



    public T dequeueWait(int iSecondTimeout) {
        T objMsg = poolFirstSync();

        if (objMsg == null) {
            for (int i = 0; i < iSecondTimeout; i++) {
                synchronized (mutex) {
                    try {
                        mutex.wait(1000);
                    } catch (InterruptedException e) {
                    }
                }
                objMsg = poolFirstSync();

                if (objMsg != null) {
                    miQueueSize.decrementAndGet();
                    return objMsg;
                }
            }
        }
        if (objMsg != null) {
            miQueueSize.decrementAndGet();
        }

        return objMsg;
    }

    private T poolFirstSync() {
        synchronized (queueData) {
            try {
                return queueData.poll();
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public int getSize() {
        return miQueueSize.intValue();
    }

    public boolean isEmpty() {
        synchronized (mutex) {
            return queueData.isEmpty();
        }
    }

    public void notify2Closed() {
        synchronized (mutex) {
            mutex.notifyAll();
        }
    }

    public void setLock(boolean bLocked) {
        mbLocked = bLocked;
    }

    public boolean remove(T objMsg) {
        synchronized (mutex) {
            return queueData.remove(objMsg);
        }

    }

    public void clear() {
        synchronized (queueData) {
            queueData.clear();
            miQueueSize.set(0);
        }
    }
}
