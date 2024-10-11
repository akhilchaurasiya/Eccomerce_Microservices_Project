package com.revature.product.controller;

import com.revature.product.model.Category;
import com.revature.product.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    // Test for a successful scenario (getting a category by ID)
    @Test
    public void testGetCategoryById_Success() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");

        when(categoryService.getCategoryById(categoryId)).thenReturn(Optional.of(category));

        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);

        // Assert that the response is 200 OK and the category is returned
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Category", response.getBody().getName());
    }

    // Test for a failure scenario (category not found)
    @Test
    public void testGetCategoryById_Failure() {
        Long categoryId = 1L;

        when(categoryService.getCategoryById(categoryId)).thenReturn(Optional.empty());

        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);

        // Assert that the response is 404 Not Found
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test for getting all categories (Success)
    @Test
    public void testGetAllCategories_Success() {
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");
        categoryList.add(category);

        when(categoryService.getAllCategories()).thenReturn(categoryList);

        List<Category> response = categoryController.getAllCategories();

        // Assert that the list of categories is returned successfully
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
        assertEquals("Test Category", response.get(0).getName());
    }

    // Test for getting all categories (Failure)
    @Test
    public void testGetAllCategories_Failure() {
        when(categoryService.getAllCategories()).thenReturn(new ArrayList<>());

        List<Category> response = categoryController.getAllCategories();

        // Assert that the list is empty
        assertTrue(response.isEmpty());
    }

    // Test for creating a category (Success)
    @Test
    public void testCreateCategory_Success() {
        Category category = new Category();
        category.setName("New Category");

        when(categoryService.createCategory(any(Category.class))).thenReturn(category);

        ResponseEntity<Category> response = categoryController.createCategory(category);

        // Assert that the category is created successfully
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("New Category", response.getBody().getName());
    }

    // Test for deleting a category (Success)
    @Test
    public void testDeleteCategory_Success() {
        Long categoryId = 1L;
        doNothing().when(categoryService).deleteCategory(categoryId);

        ResponseEntity<Void> response = categoryController.deleteCategory(categoryId);

        // Assert that the category is deleted successfully
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
