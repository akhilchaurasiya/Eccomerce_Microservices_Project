package com.revature.product.service;

import com.revature.product.model.Category;
import com.revature.product.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    // Test for creating a category (Success)
    @Test
    public void testCreateCategory_Success() {
        Category category = new Category();
        category.setName("New Category");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryService.createCategory(category);

        assertNotNull(createdCategory);
        assertEquals("New Category", createdCategory.getName());
    }

    // Test for getting a category by ID (Success)
    @Test
    public void testGetCategoryById_Success() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Optional<Category> retrievedCategory = categoryService.getCategoryById(categoryId);

        assertTrue(retrievedCategory.isPresent());
        assertEquals("Test Category", retrievedCategory.get().getName());
    }

    // Test for getting a category by ID (Failure)
    @Test
    public void testGetCategoryById_Failure() {
        Long categoryId = 1L;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        Optional<Category> retrievedCategory = categoryService.getCategoryById(categoryId);

        assertFalse(retrievedCategory.isPresent());
    }

    // Test for updating a category (Success)
    @Test
    public void testUpdateCategory_Success() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Old Category");

        Category updatedCategoryDetails = new Category();
        updatedCategoryDetails.setName("Updated Category");
        updatedCategoryDetails.setImageName("updated_image.jpg");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(updatedCategoryDetails);

        Category updatedCategory = categoryService.updateCategory(categoryId, updatedCategoryDetails);

        assertNotNull(updatedCategory);
        assertEquals("Updated Category", updatedCategory.getName());
        assertEquals("updated_image.jpg", updatedCategory.getImageName());
    }

    // Test for updating a category (Failure)
    @Test
    public void testUpdateCategory_Failure() {
        Long categoryId = 1L;
        Category updatedCategoryDetails = new Category();
        updatedCategoryDetails.setName("Updated Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.updateCategory(categoryId, updatedCategoryDetails);
        });

        assertEquals("Category not found with id 1", exception.getMessage());
    }

    // Test for deleting a category (Success)
    @Test
    public void testDeleteCategory_Success() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).delete(category);

        categoryService.deleteCategory(categoryId);

        verify(categoryRepository, times(1)).delete(category);
    }

    // Test for deleting a category (Failure)
    @Test
    public void testDeleteCategory_Failure() {
        Long categoryId = 1L;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });

        assertEquals("Category not found with id 1", exception.getMessage());
    }
}
