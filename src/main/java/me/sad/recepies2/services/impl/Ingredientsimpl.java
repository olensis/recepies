package me.sad.recepies2.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import me.sad.recepies2.model.Ingredient;
import me.sad.recepies2.services.FileServiceIngredient;
import me.sad.recepies2.services.IngredientService;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class Ingredientsimpl implements IngredientService {
    private static HashMap<Long, Ingredient> ingredients = new HashMap<Long, Ingredient>();
    private static long count = 0;
    private final FileServiceIngredient fileServiceIngredient;

    public Ingredientsimpl(FileServiceIngredient fileServiceIngredient) {
        this.fileServiceIngredient = fileServiceIngredient;
    }


    @Override
    public void addIngredients(Ingredient ingredient) {
        ingredients.put(count++, ingredient);
        saveToFile();
    }

    @Override
    public HashMap<Long, Ingredient> editIngredient(Long count, Ingredient ingredient) {
        for (Ingredient ingredientEdit : ingredients.values()) {
            if (ingredients.containsKey(count)) {
                ingredients.put(count, ingredient);
                saveToFile();
                return ingredients;
            }
        }
        return null;
    }

    @PostConstruct
    private void init(){
        readFromFile();
    }
    @Override
    public boolean deleteIngredient(Long count) {
        for (Ingredient ingredientDelete : ingredients.values()) {
            if (ingredients.containsKey(count)) {
                ingredients.remove(count);
                return true;
            }
        }
        return false;
    }

    @Override
        public Ingredient getIngredients(Long count) {
            return ingredients.get(count);
        }


    private void saveToFile() {
            try {
                String json = new ObjectMapper().writeValueAsString(ingredients);
                fileServiceIngredient.saveToFile(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }

        private void readFromFile() {
            String json = fileServiceIngredient.readFromFile();
            try {
                ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }

    }

