package me.sad.recepies2.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Recipei")
@Tag(name = "Рецепты", description = "Операции удаления, создания, получения и редактирования рецептов ")
public class RecipeController {
    private Recipeimpl recipeimpl;
    private ReceipeService receipeService;

    public RecipeController(Recipeimpl recipeimpl) {
        this.recipeimpl = recipeimpl;
    public RecipeController(ReceipeService receipeService) {
            this.receipeService = receipeService;
        }

        @PostMapping
        public ResponseEntity createRecipe(@RequestBody Recipe recipe) {
            recipeimpl.addRecipei(recipe);
            @Operation(summary = "Операция для создания рецепта")
            public ResponseEntity <Recipe> createRecipe(@RequestBody Recipe recipe) {
                receipeService.addRecipei(recipe);
                return ResponseEntity.ok(recipe);
            }

            @GetMapping("get")
            public ResponseEntity getRecipe(@RequestParam int count) {
                Recipe recipe = recipeimpl.getRecipe(count);
                @GetMapping("/{count}")
                @Operation(summary = "Операция для получения рецепта")
                public ResponseEntity <Recipe> getRecipe(@RequestParam Long count) {
                    Recipe recipe = receipeService.getRecipe(count);
                    if (recipe == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(recipe);
                }

                @PutMapping("/{count}")
                @Operation(summary = "Операция для редактирования рецепта")
                public ResponseEntity <Recipe> editRecipe (@PathVariable Long count ,@RequestBody Recipe recipe){
                    Map<Long, Recipe> recipes = receipeService.editRecipe(count,recipe);
                    if (recipe == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(recipe);
                }
                @DeleteMapping("/{count}")
                @Operation(summary = "Операция для удаления рецепта")
                public ResponseEntity<Void> deleteRecipe(@PathVariable Long count) {
                    if (receipeService.deleteRecipe(count)) {
                        return ResponseEntity.ok().build();
                    }
                    return ResponseEntity.notFound().build();
                }
            }