package com.example.CRUDABS.controller;


import com.example.CRUDABS.entity.Book;
import com.example.CRUDABS.models.ResponseModel;
import com.example.CRUDABS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/book/add", produces = "application/json")
    public ResponseEntity<ResponseModel> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping(value = "/book/add/all", produces = "application/json")
    public ResponseEntity<ResponseModel> addBooks(@RequestBody List<Book> book) {
        return bookService.addBooks(book);
    }

    @GetMapping(value = "/book/all", produces = "application/json")
    public ResponseEntity<ResponseModel> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/book/{id}", produces = "application/json")
    public ResponseEntity<ResponseModel> getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PutMapping(value = "/book/edit/{id}", produces = "application/json")
    public ResponseEntity<ResponseModel> editBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.editBook(id, book);
    }

    @DeleteMapping(value = "/book/delete/{id}", produces = "application/json")
    public ResponseEntity<ResponseModel> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @DeleteMapping(value = "/book/delete/all", produces = "application/json")
    public ResponseEntity<ResponseModel> deleteBooks() {
        return bookService.deleteBooks();
    }
}
