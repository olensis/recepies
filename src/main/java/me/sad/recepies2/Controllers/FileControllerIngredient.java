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
@RequestMapping("/files/ingredient")
public class FileControllerIngredient {
    private final FileService fileServiceIngredientimpl;

    public FileControllerIngredient(FileService fileServiceIngredientimpl) {
        this.fileServiceIngredientimpl = fileServiceIngredientimpl;
    }


    @GetMapping(value = "/export", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> dowloadFileIngredient () throws FileNotFoundException {
        File file = fileServiceIngredientimpl.getFileIngredient();
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

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFileIngredient(@RequestParam MultipartFile file) {
        fileServiceIngredientimpl.cleanIngredientFile();
        File fileIngredient = fileServiceIngredientimpl.getFileIngredient();
        try (FileOutputStream fos = new FileOutputStream(fileIngredient)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
