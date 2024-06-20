package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> getAllCategory();

    ResponseEntity<?> saveCategory(CategoryDTO categoryDTO);

    ResponseEntity<?> updateCategory(CategoryDTO categoryDTO, int categoryId);

    ResponseEntity<?> deleteCategory(int categoryId);

    boolean existsByCategoryName(String categoryName);
}
