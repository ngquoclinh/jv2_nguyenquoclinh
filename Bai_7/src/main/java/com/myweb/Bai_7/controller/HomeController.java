package com.myweb.Bai_7.controller;

import com.myweb.Bai_7.dto.RecipesDto;
import com.myweb.Bai_7.service.ExternalApiService;
import com.myweb.Bai_7.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import tools.jackson.databind.JsonNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
        @Autowired
        public ExternalApiService externalApiService;
        @Autowired
        public RecipesService recipesService;

        private final SpringTemplateEngine templateEngine;

        public HomeController(SpringTemplateEngine templateEngine) {
            this.templateEngine = templateEngine;
        }

        // Unit 7.1
        private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

        @PostMapping("/api/images")
        public ResponseEntity<?> uploadProductImage(
                @RequestParam("files") MultipartFile[] files
        ){

            if (files == null || files.length == 0) {
                return new ResponseEntity<>("Missing file", HttpStatus.BAD_REQUEST);
            }

            List<String> allowedExtensions = List.of("png", "jpg", "jpeg");
            List<String> uploadedFiles = new ArrayList<>();


            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    System.out.println("Begin creating new folder");
                    Files.createDirectories(uploadPath);
                }

                for (MultipartFile file : files) {
                    String originalName = file.getOriginalFilename();

                    if(originalName == null || file.isEmpty()) {
                        continue;
                    }

                    if(!isValidExtension(originalName, allowedExtensions)) {
                        return ResponseEntity.badRequest().body("File không hợp lệ: " + originalName);
                    }

                    String fileName = file.getOriginalFilename();
                    Path filePath = uploadPath.resolve(fileName);

                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    uploadedFiles.add(filePath.toString());

                 }

                return ResponseEntity.ok(uploadedFiles);
            } catch (IOException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        private boolean isValidExtension(String fileName, List<String> allowedExtensions) {
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            return allowedExtensions.contains(extension);
        }


        // Unit 7.2
        @GetMapping("/api/recipes")
        public ResponseEntity<JsonNode> getRecipes() {
            try {
                JsonNode data = externalApiService.fetchDataFromExternalApi("https://dummyjson.com/recipes");
                return new ResponseEntity<>(data, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        // Unit 7.3
        @GetMapping(value = "/recipes_ui", produces = MediaType.TEXT_HTML_VALUE)
        @ResponseBody
        public String showRecipes() throws IOException, InterruptedException{
            JsonNode item = externalApiService.fetchDataFromExternalApi("https://dummyjson.com/recipes");
            List<RecipesDto> listRecipes = recipesService.getRecipesByJsonNode(item);
            Context context = new Context();
            context.setVariable("listRecipes", listRecipes);
            return templateEngine.process("recipes_ui", context);
        }


        // Unit 7.4
        @GetMapping(value = "/recipes_ui_new", produces = MediaType.TEXT_HTML_VALUE)
        @ResponseBody
        public String showRecipesNew() throws IOException, InterruptedException{
            JsonNode item = externalApiService.fetchDataFromExternalApi("https://dummyjson.com/recipes");
            List<RecipesDto> listRecipes = recipesService.getRecipesByJsonNode(item);
            Context context = new Context();
            context.setVariable("listRecipes", listRecipes);
            return templateEngine.process("recipes_ui_new", context);
        }



}
