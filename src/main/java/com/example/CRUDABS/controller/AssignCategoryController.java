package com.example.CRUDABS.controller;

import com.example.CRUDABS.models.ResponseModel;
import com.example.CRUDABS.service.BookCategoryAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AssignCategoryController {

    @Autowired
    BookCategoryAssignService bookCategoryAssignService;

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