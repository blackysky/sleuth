package ru.incube.sleuth.async;

import java.util.concurrent.Callable;

public interface IConcurrencyManager {
    void submit(Callable<?> task);

    void shutdown();
}
