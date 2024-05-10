package ru.incube.sleuth.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyManager implements IConcurrencyManager {
    /*
    Manages thread pools and asynchronous task execution.
     */
    private final ExecutorService executorService;

    public ConcurrencyManager() {
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void submit(Callable<?> task) {
        executorService.submit(() -> {
            try {
                System.out.println("Task started");
                task.call();
                System.out.println("Task finished");
            } catch (Exception e) {
                System.out.println("Error executing task: " + e.getMessage());
            }
        });
    }

    @Override
    public void shutdown() {
        System.out.println("Shutting down executor service...");
        executorService.shutdown();
    }
}