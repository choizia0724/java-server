package com.choizia.java_server.controller;

import com.choizia.java_server.repository.RecipeRepository;

import com.choizia.java_server.vo.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/Recipe")
    public List<Recipe> getRecipe(@RequestParam int code) {
        return recipeRepository.findRecipesByCode(code);
    }
}