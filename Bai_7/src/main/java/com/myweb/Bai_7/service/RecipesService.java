package com.myweb.Bai_7.service;

import com.myweb.Bai_7.dto.RecipesDto;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipesService {
    public List<RecipesDto> getRecipesByJsonNode(JsonNode data) {
        List<RecipesDto> result = new ArrayList<>();
        JsonNode recipesArray = data.get("recipes");

        for (JsonNode item : recipesArray) {
            RecipesDto recipes = new RecipesDto();
            recipes.id = item.get("id").asInt();
            recipes.title = item.get("name").asText();
            recipes.image = item.get("image").asText();
            recipes.rating = item.get("rating").asText();
            result.add(recipes);
        }
        return result;
    }
}