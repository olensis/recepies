package me.sad.recepies2.services;

import java.io.File;
import java.nio.file.Path;

public interface FileService {
    boolean saveIngredientToFile(String json);

    String readIngredientsFromFile();

    boolean saveRecipesToFile(String json);

    String readRecipesFromFile();

    Path createTempFile(String suffix);

    void cleanFile(String fileName);

    File getFileIngredient();

    File getFileRecipe();

    void cleanIngredientFile();

    void cleanRecipeFile();
}
