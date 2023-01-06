package me.sad.recepies2.services;

public interface FileServiceIngredient {
    boolean saveToFile(String json);

    String readFromFile();
}
