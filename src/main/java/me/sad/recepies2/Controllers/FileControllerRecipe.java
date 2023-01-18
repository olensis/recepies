package me.sad.recepies2.Controllers;

import me.sad.recepies2.services.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/recipe")
public class FileControllerRecipe {
    private final FileService fileServiceimplReceipe;

    public FileControllerRecipe(FileService fileServiceimplReceipe) {
        this.fileServiceimplReceipe = fileServiceimplReceipe;
    }


    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> dowloadFileRecipe () throws FileNotFoundException {
        File file = fileServiceimplReceipe.getFileRecipe();
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
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFileRecipe(@RequestParam MultipartFile file) {
       fileServiceimplReceipe.cleanRecipeFile();
        File fileRecipe = fileServiceimplReceipe.getFileRecipe();
        try (FileOutputStream fos = new FileOutputStream(fileRecipe)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
