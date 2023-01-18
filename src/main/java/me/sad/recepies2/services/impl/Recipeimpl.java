package me.sad.recepies2.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sad.recepies2.model.Recipe;
import me.sad.recepies2.services.FileService;
import me.sad.recepies2.services.ReceipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

@Service
public class Recipeimpl implements ReceipeService {
    private final FileService fileService;
    private static Map<Long, Recipe> recipes = new HashMap<>();
    private static long count = 0;



    public Recipeimpl( FileService fileService) {
        this.fileService = fileService;

    }
    @PostConstruct
    private void unit(){
        readFromFile();
    }
    @Override
    public void addRecipei(Recipe recipe) {
        recipes.put(count++, recipe);
        saveToFile();
    }

    @Override
    public Map<Long, Recipe> editRecipe(Long count, Recipe recipe) {
        for (Recipe recipeEdit : recipes.values()) {
            if (recipes.containsKey(count)) {
                recipes.put(count, recipe);
                saveToFile();
                return recipes;
            }
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(Long count) {
        for (Recipe recipeDelete : recipes.values()) {
            if (recipes.containsKey(count)) {
                recipes.remove(count);
                return true;
            }
        }
        return false;
    }

    @Override
    public Recipe getRecipe(Long count) {
        return recipes.get(count);
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveRecipesToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public File createRecipesTxtFile() throws FileNotFoundException {
        Path path = fileService.createTempFile("Recipes");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (Recipe recipe : recipes.values()) {
                writer.append(recipe.toString());
                writer.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path.toFile();
    }

    private void readFromFile() {
        try {
            String json = fileService.readRecipesFromFile();
            if(!json.isBlank()){
                recipes = new ObjectMapper().readValue(json, new TypeReference<>() {
                });
                count = recipes.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    }
