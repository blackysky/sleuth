package ru.incube.sleuth.jars;

import ru.incube.sleuth.enums.ModType;

import java.io.File;
import java.io.IOException;

public interface IJarAnalyzer {
    void analyzeJar(File file) throws IOException;

    boolean ModsTomlExists(File file) throws IOException;

    default boolean isJarFile(File file) {
        return file.getName().endsWith(".jar");
    }

    default ModType detectModType(File file) {
        return ModType.UNKNOWN;
    }
}

