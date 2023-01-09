package me.sad.recepies2.services;

import java.io.File;

public interface FileServiceReceipe {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();
}
