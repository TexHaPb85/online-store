package edu.hillel.utilities;


class SharedResource {
    private int counter = 0;
    private volatile boolean flag = false;

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void setFlagTrue() {
        flag = true;
    }

    public boolean isFlagTrue() {
        return flag;
    }
}

public class Test {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Thread writer = new Thread(() -> {
            int i =0;
            while (i<10){
                sharedResource.increment();
                sharedResource.setFlagTrue();
                i++;
            }

        });

        Thread reader = new Thread(() -> {
            while (!sharedResource.isFlagTrue()) {
                System.out.println("Counter: " + sharedResource.getCounter());
            }

        });

        writer.start();
        reader.start();

        writer.join();
        reader.join();
    }
}