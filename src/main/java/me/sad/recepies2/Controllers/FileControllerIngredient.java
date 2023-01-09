package me.sad.recepies2.Controllers;

import me.sad.recepies2.services.impl.FileServiceIngredientimpl;
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
@RequestMapping("/files/ingredient")
public class FileControllerIngredient {
    private final FileServiceIngredientimpl fileServiceIngredientimpl;

    public FileControllerIngredient(FileServiceIngredientimpl fileServiceIngredientimpl) {
        this.fileServiceIngredientimpl = fileServiceIngredientimpl;
    }
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> dowloadFileIngredient () throws FileNotFoundException {
        File file = fileServiceIngredientimpl.getDataFile();
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName = \"IngredientLog.json\"")
                    .body(inputStreamResource);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

}
