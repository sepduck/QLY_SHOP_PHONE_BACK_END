package com.qlyshopphone_backend.controller.rest;

import com.github.javafaker.Faker;
import com.qlyshopphone_backend.dto.CategoryDTO;
import com.qlyshopphone_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RestCategoryController {
    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PostMapping("/category")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO,
                                                @PathVariable int categoryId) {
        return categoryService.updateCategory(categoryDTO, categoryId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> generateFake() {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String product = faker.name().fullName();
            if (categoryService.existsByCategoryName(product)){
                continue;
            }
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .categoryName(product)
                    .build();
            try {
                categoryService.saveCategory(categoryDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        return ResponseEntity.ok("Fake category generated");
    }
}
