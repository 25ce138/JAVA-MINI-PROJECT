package service;

public class TransactionBuffer {
    private final String[] buffer = new String[4];
    private int count = 0;

    public synchronized void put(String tx) throws InterruptedException {
        while (count == buffer.length) wait();
        buffer[count++] = tx;
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (count == 0) wait();
        String tx = buffer[--count];
        notifyAll();
        return tx;
    }
}
