package me.sad.recepies2.services;

public interface FileServiceReceipe {
    boolean saveToFile(String json);

    String readFromFile();
}
