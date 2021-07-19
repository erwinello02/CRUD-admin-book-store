package com.example.CRUDABS.controller;

import com.example.CRUDABS.entity.Category;
import com.example.CRUDABS.models.ResponseModel;
import com.example.CRUDABS.service.BookCategoryAssignService;
import com.example.CRUDABS.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BookCategoryAssignService bookCategoryAssignService;

    @PostMapping(value = "/category/add", produces = "application/json")

    public ResponseEntity<ResponseModel> addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PostMapping(value = "/category/add/all", produces = "application/json")
    public ResponseEntity<ResponseModel> addCategories(@RequestBody List<Category> category) {
        return categoryService.addCategories(category);
    }

    @GetMapping(value = "/category/all", produces = "application/json")
    public ResponseEntity<ResponseModel> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping(value = "/category/edit/{id}", produces = "application/json")
    public ResponseEntity<ResponseModel> editCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.editCategory(id, category);
    }

    @DeleteMapping(value = "/category/delete/{id}", produces = "application/json")
    public ResponseEntity<ResponseModel> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @PostMapping(value = "/category/assign/book", produces = "application/json")
    public ResponseEntity<ResponseModel> assignCategoryForBook(@RequestParam Long cat_id, @RequestParam Long book_id) {
        return bookCategoryAssignService.assignCategoryForBook(cat_id, book_id);
    }

    @DeleteMapping(value = "/category/remove/book", produces = "application/json")
    public ResponseEntity<ResponseModel> removeCategoryForBook(@RequestParam Long cat_id, @RequestParam Long book_id) {
        return bookCategoryAssignService.removeCategoryForBook(cat_id, book_id);
    }

    @GetMapping(value = "/category/assign/all", produces = "application/json")
    public ResponseEntity<ResponseModel> getAllAssignCategoryForBooks() {
        return bookCategoryAssignService.getAllAssignCategoryForBooks();
    }
}