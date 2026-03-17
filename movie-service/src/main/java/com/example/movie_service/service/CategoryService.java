package com.example.movie_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie_service.entity.Category;
import com.example.movie_service.entity.Movie;
import com.example.movie_service.repository.CategoryRepository;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.transaction.Transactional;


@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<Category> saveCategories(JsonNode items){
        List<Category> categories = new ArrayList<>();
        for (JsonNode item : items) {

            String cat_name = item.get("name").toString().replace("\"", "");
            String cat_slug = item.get("slug").toString().replace("\"", "");
            Category category = categoryRepository
                .findByName(cat_name)
                .orElseGet(() -> {
                    Category newCat = new Category();
                    newCat.setName(cat_name);
                    newCat.setSlug(cat_slug);
                    return categoryRepository.save(newCat);
                });

        categories.add(category);
        }
        return categories;
    }


}
