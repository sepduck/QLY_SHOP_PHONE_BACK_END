package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dto.CategoryDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.Category;
import com.qlyshopphone_backend.repository.CategoryRepository;
import com.qlyshopphone_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl extends BaseReponse implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<?> getAllCategory() {
        return getResponseEntity(categoryRepository.findAll());
    }

    @Override
    public ResponseEntity<?> saveCategory(CategoryDTO categoryDTO) {
        try{
            Category category = new Category();
            category.setCategoryName(categoryDTO.getCategoryName());
            categoryRepository.save(category);
            return getResponseEntity("Category saved successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateCategory(CategoryDTO categoryDTO, int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            Category existingCategory = category.get();
            existingCategory.setCategoryName(categoryDTO.getCategoryName());
            categoryRepository.save(existingCategory);
            return getResponseEntity("Category updated successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteCategory(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            categoryRepository.deleteById(categoryId);
            return getResponseEntity("Category deleted successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }

    @Override
    public boolean existsByCategoryName(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }
}
