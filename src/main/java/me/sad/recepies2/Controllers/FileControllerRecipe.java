package me.sad.recepies2.Controllers;

import me.sad.recepies2.services.impl.FileServiceimplReceipe;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/files/recipe")
public class FileControllerRecipe {
    private final FileServiceimplReceipe fileServiceimplReceipe;

    public FileControllerRecipe(FileServiceimplReceipe fileServiceimplReceipe) {
        this.fileServiceimplReceipe = fileServiceimplReceipe;
    }
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> dowloadFileRecipe () throws FileNotFoundException {
        File file = fileServiceimplReceipe.getDataFile();
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName = \"RecipeLog.json\"")
                    .body(inputStreamResource);
        }else {
            return ResponseEntity.noContent().build();
        }
    }
}
