package me.sad.recepies2.services;
import me.sad.recepies2.model.Ingredient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface IngredientService {
    void addIngredients(Ingredient ingredient);

    Map<Long, Ingredient> editIngredient(Long count, Ingredient ingredient);

    boolean deleteIngredient(Long count);

    Ingredient getIngredients(Long count);


    Ingredient editIngredient(long id, Ingredient newingredient);
}
