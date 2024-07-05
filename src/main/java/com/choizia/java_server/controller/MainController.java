package com.choizia.java_server.controller;

import com.choizia.java_server.repository.RecipeRepository;
import com.choizia.java_server.service.DailyDataService;

import com.choizia.java_server.vo.DailyData;
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
    private DailyDataService dailyDataService;

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/DailyData/latest")
    public DailyData getDailyData(@RequestParam int code) {
        return dailyDataService.getDailyDataByCode(code);
    }

    @GetMapping("/Recipe")
    public List<Recipe> getRecipe(@RequestParam int code) {
        return recipeRepository.findRecipesByCode(code);
    }
}