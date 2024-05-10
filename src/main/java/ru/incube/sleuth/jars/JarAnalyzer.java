package ru.incube.sleuth.jars;

import ru.incube.sleuth.enums.ModType;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarAnalyzer implements IJarAnalyzer {
    /*
    Opens jar files, checks their type (server/client), and hands off to respective handlers.
     */
    @Override
    public void analyzeJar(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null");
        }

        if (!isJarFile(file)) {
            throw new IllegalArgumentException("File is not a jar file");
        }

        try (ZipFile ignored = new ZipFile(file)) {
            if (ModsTomlExists(file)) {
                switch (detectModType(file)) {
                    case BOTH:
                        System.out.println("Mod type: CLIENT & SERVER");
                        break;
                    case CLIENT:
                        System.out.println("Mod type: CLIENT");
                        break;
                    case SERVER:
                        System.out.println("Mod type: SERVER");
                        break;
                    case UNKNOWN:
                        System.out.println("Mod type: UNKNOWN");
                        break;
                }
            } else {
                throw new IOException("mods.toml not found in jar file: " + file.getName());
            }
        } catch (IOException e) {
            throw new IOException("Failed to open jar file: " + file.getName(), e);
        }
    }

    @Override
    public boolean ModsTomlExists(File file) throws IOException {
        try (ZipFile zipFile = new ZipFile(file)) {
            return zipFile.getEntry("META-INF/mods.toml") != null;
        } catch (IOException e) {
            throw new IOException("Failed to open jar file: " + file.getName(), e);
        }
    }

    @Override
    public ModType detectModType(File file) {
        try (ZipFile zipFile = new ZipFile(file)) {
            ZipEntry modsTomlEntry = zipFile.getEntry("META-INF/mods.toml");
            if (modsTomlEntry != null) {
                // Using a temporary file to read mods.toml
                File tempFile = File.createTempFile("mods", ".toml");
                try (InputStream is = zipFile.getInputStream(modsTomlEntry);
                     OutputStream os = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }

                    os.flush();
                }

                // Parse mods.toml to determine mod type
                try (BufferedReader reader = Files.newBufferedReader(tempFile.toPath())) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("displayTest")) {
                            String value = line.split("=")[1].trim();
                            value = value.substring(1, value.length() - 1); // Remove single quotes

                            switch (value) {
                                case "MATCH_VERSION", "NONE":
                                    return ModType.BOTH;
                                case "IGNORE_SERVER_VERSION":
                                    return ModType.CLIENT;
                                case "IGNORE_ALL_VERSION":
                                    return ModType.SERVER;
                            }
                        }
                    }
                }

                System.out.println("No displayTest found in mods.toml");
                return ModType.UNKNOWN;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to open or read jar file: " + file.getName(), e);
        }
        return ModType.UNKNOWN;
    }
}
