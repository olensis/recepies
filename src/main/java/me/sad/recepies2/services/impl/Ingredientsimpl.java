package me.sad.recepies2.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sad.recepies2.model.Ingredient;
import me.sad.recepies2.services.FileService;
import me.sad.recepies2.services.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;


@Service
public class Ingredientsimpl implements IngredientService {
    private static HashMap<Long, Ingredient> ingredients = new HashMap<Long, Ingredient>();
    private static long count = 0;
    private final FileService fileServiceIngredient;

    public Ingredientsimpl(FileService fileServiceIngredient) {
        this.fileServiceIngredient = fileServiceIngredient;
    }
    @PostConstruct
    private void unit(){
        readFromFile();
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
                fileServiceIngredient.saveIngredientToFile(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }

    private void readFromFile() {
        try {
            String json = fileServiceIngredient.readIngredientsFromFile();
            if(!json.isBlank()){
                ingredients = new ObjectMapper().readValue(json, new TypeReference<>() {
                });
                count = ingredients.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient newingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, newingredient);
            saveToFile();
            return ingredients.get(id);
        }
        return null;
    }


        }



