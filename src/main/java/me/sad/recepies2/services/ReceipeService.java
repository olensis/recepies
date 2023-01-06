package me.sad.recepies2.services;

import me.sad.recepies2.model.Recipe;

import java.util.Map;

public interface ReceipeService {
    void addRecipei(Recipe recipe);

    Map<Long, Recipe> editRecipe(Long count, Recipe recipe);


    boolean deleteRecipe(Long count);

    Recipe getRecipe(Long count);
}
