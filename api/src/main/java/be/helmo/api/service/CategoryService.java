package be.helmo.api.service;

import be.helmo.api.infrastructure.model.Category;
import be.helmo.api.infrastructure.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categorieRepository;

    public CategoryService() {
    }

    public Optional<Category> getCategorieById(int id) {
        return categorieRepository.findById(id);
    }

    public List<Category> getAllUsersCategories(int id) {
        return categorieRepository.findByUser_Id(id);
    }
}
