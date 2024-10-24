package be.helmo.api.controller;

import be.helmo.api.dto.CategoryDTO;
import be.helmo.api.dto.mappers.CategoryMapper;
import be.helmo.api.infrastructure.model.Category;
import be.helmo.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/financewise/category/users/{id}")
    public ResponseEntity<List<CategoryDTO>> getUserCategories(@PathVariable int id, @RequestParam(required = false) String fields) {
        List<Category> categories = categoryService.getAllUsersCategories(id);
        List<CategoryDTO> categorieDTOS = CategoryMapper.toDTOList(categories);
        return ResponseEntity.ok(categorieDTOS);
    }

}
