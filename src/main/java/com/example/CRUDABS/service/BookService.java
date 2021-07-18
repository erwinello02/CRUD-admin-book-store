package com.example.CRUDABS.service;

import com.example.CRUDABS.entity.Book;
import com.example.CRUDABS.models.ResponseModel;
import com.example.CRUDABS.repository.BookRepo;
import com.example.CRUDABS.responseUtils.ResponseFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    ResponseFunctions responseFunctions;

    public ResponseEntity<ResponseModel> addBook(Book book) {

        HttpStatus status;
        Object result;
        try {
            status = HttpStatus.CREATED;
            result = bookRepo.save(book);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }

    public ResponseEntity<ResponseModel> addBooks(List<Book> book) {
        HttpStatus status;
        Object result;
        try {
            status = HttpStatus.CREATED;
            result = bookRepo.saveAll(book);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }


    public ResponseEntity<ResponseModel> getAllBooks() {
        HttpStatus status;
        Object result;
        try {
            List<Book> list = bookRepo.findAll();
            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            status = HttpStatus.CREATED;
            result = list;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }

    public ResponseEntity<ResponseModel> getBook(Long id) {
        HttpStatus status;
        Object result;
        try {
            Optional<Book> books = bookRepo.findById(id);
            Book book = null;
            if (books.isPresent()) {
                book = books.get();
            }
            status = HttpStatus.CREATED;
            result = book;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }

    public ResponseEntity<ResponseModel> editBook(Long id, Book book) {
        HttpStatus status;
        Object result;
        try {
            Optional<Book> books = bookRepo.findById(id);
            String book_name;
            if(book.getBook_name() != null){ book_name = book.getBook_name();
            } else{ book_name = books.get().getBook_name(); }

            String book_category;
            if(book.getBook_category() != null){ book_category = book.getBook_category();
            } else{ book_category = books.get().getBook_category(); }

            String book_author;
            if(book.getAuthor() != null){ book_author = book.getAuthor();
            } else{ book_author = books.get().getAuthor(); }

            String book_description;
            if(book.getBook_description() != null){ book_description = book.getBook_description();
            } else{ book_description = books.get().getBook_description(); }

            books.get().setBook_name(book_name);
            books.get().setAuthor(book_author);
            books.get().setBook_category(book_category);
            books.get().setBook_description(book_description);

            status = HttpStatus.CREATED;
            result = bookRepo.save(books.get());;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }

    public ResponseEntity<ResponseModel> deleteBook(Long id) {
        HttpStatus status;
        Object result;
        try {
            Optional<Book> books = bookRepo.findById(id);
            if (books.isPresent()) {
            bookRepo.delete(books.get());
            }
            status = HttpStatus.CREATED;
            result = books.get() + "Successfully Deleted!" ;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }

    public ResponseEntity<ResponseModel> deleteBooks() {
        HttpStatus status;
        Object result;
        try {
            bookRepo.deleteAll();
            status = HttpStatus.CREATED;
            result = "Everything is Successfully Deleted!" ;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }
}
