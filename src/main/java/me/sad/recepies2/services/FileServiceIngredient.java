package me.sad.recepies2.services;

import java.io.File;

public interface FileServiceIngredient {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
