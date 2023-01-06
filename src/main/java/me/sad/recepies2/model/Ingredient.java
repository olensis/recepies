package me.sad.recepies2.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String title;
    private int amount;
    private String unitOfMeasurement;
}