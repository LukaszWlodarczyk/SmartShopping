package com.zzpj.smartshopping.controllers;

import com.zzpj.smartshopping.model.Category;
import com.zzpj.smartshopping.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public ResponseEntity<Iterable<Category>> getCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}
