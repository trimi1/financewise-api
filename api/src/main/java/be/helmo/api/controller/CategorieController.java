package be.helmo.api.controller;

import be.helmo.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieController {

    private CategoryService categoryService;

    @Autowired
    public CategorieController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

}
