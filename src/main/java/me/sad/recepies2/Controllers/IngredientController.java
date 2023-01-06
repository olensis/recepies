package me.sad.recepies2.Controllers;

import me.sad.recepies2.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/Ingredient")
@Tag(name = "Ингредиенты", description = "Операции удаления, создания, получения и редактирования ингредиентов ")
public class IngredientController {
    private ServiceIngredients serviceIngredients;
    private IngredientService ingredientService;

    public IngredientController(ServiceIngredients serviceIngredients) {
        this.serviceIngredients = serviceIngredients;
    public IngredientController(IngredientService ingredientService) {
            this.ingredientService = ingredientService;

        }
        @PostMapping()
        public ResponseEntity createIngredient(@RequestBody Ingredient ingredient){
            serviceIngredients.addIngredients(ingredient);

            @PostMapping
            @Operation(summary = "Операция для создания ингредиента")
            public ResponseEntity<Ingredient> createIngredient (@RequestBody Ingredient ingredient){
                ingredientService.addIngredients(ingredient);
                return ResponseEntity.ok(ingredient);
            }

            @GetMapping("/{count}")
            @Operation(summary = "Операция для получения ингредиента")
            public ResponseEntity<Ingredient> getIngredient (@RequestParam Long count){
                Ingredient ingredient = ingredientService.getIngredients(count);
                if (ingredient == null) {
                    return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(ingredient);
            }
            @GetMapping("get")
            public ResponseEntity getIngredient ( @RequestParam int count){
                Ingredient ingredient = serviceIngredients.getIngredients(count);

                @PutMapping("/{count}")
                @Operation(summary = "Операция для редактирования ингредиента")
                public ResponseEntity<Ingredient> editIngredient (@PathVariable Long count, @RequestBody Ingredient
                ingredient){
                    Map<Long, Ingredient> ingredients = ingredientService.editIngredient(count, ingredient);
                    if (ingredient == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(ingredient);
                }

                @DeleteMapping("/{count}")
                @Operation(summary = "Операция для удаления ингредиента")
                public ResponseEntity<Void> deleteIngredient (@PathVariable Long count){
                    if (ingredientService.deleteIngredient(count)) {
                        return ResponseEntity.ok().build();
                    }
                    return ResponseEntity.notFound().build();
                }
            }
        }

