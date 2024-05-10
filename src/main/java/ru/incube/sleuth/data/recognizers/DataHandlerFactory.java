package ru.incube.sleuth.data.recognizers;

import ru.incube.sleuth.data.IDataExtractor;

import java.io.File;

public class DataHandlerFactory implements IDataExtractor {
    /*
    Based on the directory type, this factory will produce the correct DataExtractor.
     */
    @Override
    public void extractDataFolder(File dataFolder) {

    }
}
