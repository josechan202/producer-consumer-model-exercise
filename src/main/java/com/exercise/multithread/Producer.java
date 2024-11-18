package com.exercise.multithread;

import java.util.concurrent.BlockingQueue;
import java.util.Random;

public class Producer implements Runnable {
    private final BlockingQueue<Integer> sharedBuffer;
    private volatile boolean running = true;

    public Producer(BlockingQueue<Integer> sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (running) {
                int item = random.nextInt(100);
                sharedBuffer.put(item); // Put item into buffer
                System.out.println(Thread.currentThread().getName() + " produced: " + item);
                Thread.sleep(500); // Simulate production delay
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