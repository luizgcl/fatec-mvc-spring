package br.com.farmed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.farmed.entity.Category;
import br.com.farmed.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SlugService slugService;

    public String saveCategory(Category category) {
        if (category.getSlugCategory() == null || category.getSlugCategory().isEmpty()) {
            category.setSlugCategory(slugService.slugify(category.getNameCategory()));
        }
        
        Category saveCategory = categoryRepository.save(category);

        return saveCategory.getSlugCategory();
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryBySlug(String slug) {
        return categoryRepository.findBySlugCategory(slug).orElse(null);
    }

    public void deleteCategory(String slug) {
        categoryRepository.deleteBySlugCategory(slug);
    }
}
