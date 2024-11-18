package com.exercise.multithread;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Integer> sharedBuffer;
    private volatile boolean running = true;

    public Consumer(BlockingQueue<Integer> sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        try {
            while (running) {
                int item = sharedBuffer.take(); // Take an item from the buffer
                System.out.println(Thread.currentThread().getName() + " consumed: " + item);
                Thread.sleep(700); // Simulate consumption delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(Thread.currentThread().getName() + " interrupted: " + e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " stopped.");
    }

    public void stop() {
        running = false;
    }
}