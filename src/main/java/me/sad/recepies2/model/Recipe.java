package me.sad.recepies2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@Data
@NoArgsConstructor
    public class Recipe {
        private String title;
        private int cookingTime;
        ArrayList<Ingredient> ingredients;
        ArrayList<String> cookingSteps;
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(title).append("\n");
        buffer.append("Время приготовления: " + cookingTime + " мин.").append("\n");
        buffer.append("Ингредиенты: ").append("\n");
        for (Ingredient ingredient : ingredients) {
            buffer.append("\t").append(ingredient).append("\n");
        }
        buffer.append("Инструкция приготовления").append("\n");
        for (int i = 0; i < cookingSteps.size(); i++) {
            buffer.append(i + 1).append(". ").append(cookingSteps.get(i)).append("/n");
        }
        return buffer.toString();
    }
}


