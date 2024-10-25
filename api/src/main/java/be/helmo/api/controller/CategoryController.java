package be.helmo.api.controller;

import be.helmo.api.dto.CategoryDTO;
import be.helmo.api.dto.mappers.CategoryMapper;
import be.helmo.api.infrastructure.model.Category;
import be.helmo.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Get categories by user idCategory
     * @param id user idCategory
     * @return the list of categories from a user
     */
    @GetMapping("/financewise/categories/users/{id}")
    public ResponseEntity<List<CategoryDTO>> getUserCategories(@PathVariable int id) {
        List<Category> categories = categoryService.getAllUsersCategories(id);
        List<CategoryDTO> categorieDTOS = CategoryMapper.toDTOList(categories);
        return ResponseEntity.ok(categorieDTOS);
    }

    /**
     * Update categories from a user
     * @param idUser user idCategory
     * @param categories list of categories
     */
    @PutMapping("/financewise/categories/users/{idUser}")
    public void updateCategories(@PathVariable int idUser, @RequestBody List<CategoryDTO> categories) {
        categoryService.updateCategories(idUser, categories);
    }

    /**
     * Delete categories from a user
     * @param idUser user idCategory
     * @param categories list of categories
     */
    @DeleteMapping("/financewise/categories/users/{idUser}")
    public void deleteCategories(@PathVariable int idUser, @RequestBody List<CategoryDTO> categories) {
        categoryService.deleteCategories(idUser, categories);
    }
}
