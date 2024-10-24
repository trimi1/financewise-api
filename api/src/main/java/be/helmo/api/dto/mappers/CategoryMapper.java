package be.helmo.api.dto.mappers;

import be.helmo.api.dto.CategoryDTO;
import be.helmo.api.infrastructure.model.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDTO toDto(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getMontantMax(), category.getDevise().getDevise());
    }

    public static List<CategoryDTO> toDTOList(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }
}
