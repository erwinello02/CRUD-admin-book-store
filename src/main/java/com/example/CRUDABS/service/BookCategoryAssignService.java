package com.example.CRUDABS.service;

import com.example.CRUDABS.entity.Book;
import com.example.CRUDABS.entity.BookCategoryAssign;
import com.example.CRUDABS.entity.Category;
import com.example.CRUDABS.models.ResponseModel;
import com.example.CRUDABS.repository.BookCategoryAssignRepo;
import com.example.CRUDABS.repository.BookRepo;
import com.example.CRUDABS.repository.CategoryRepo;
import com.example.CRUDABS.responseUtils.ResponseFunctions;
import com.example.CRUDABS.utils.H2JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Service
public class BookCategoryAssignService {

    @Autowired
    BookCategoryAssignRepo bookCategoryAssignRepo;

    @Autowired
    BookRepo bookRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ResponseFunctions responseFunctions;


    public ResponseEntity<ResponseModel> assignCategoryForBook(Long cat_id, Long book_id) {
        HttpStatus status;
        Object result;
        try {
            Long bookId;
            Optional<Book> book = bookRepo.findById(book_id);
            if (book.isPresent()) {
                bookId = book.get().getId();
            } else {
                throw new IllegalArgumentException("The book is not exist!");
            }

            Long categoryId;
            Optional<Category> category = categoryRepo.findById(cat_id);
            if (book.isPresent()) {
                categoryId = category.get().getId();
            } else {
                throw new IllegalArgumentException("The category is not exist!");
            }

            BookCategoryAssign bookCategoryAssign = new BookCategoryAssign();
            bookCategoryAssign.setCategory_id(categoryId);
            bookCategoryAssign.setBook_id(bookId);

            status = HttpStatus.CREATED;
            result = bookCategoryAssignRepo.save(bookCategoryAssign);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }


    public ResponseEntity<ResponseModel> removeCategoryForBook(Long cat_id, Long book_id) {
        HttpStatus status;
        Object result;
        try {
            final String deleteTableSQL = "DELETE FROM TBL_BOOK_CATEGORY_ASSIGN WHERE BOOK_ID=" + book_id + "AND CATEGORY_ID=" + cat_id;
            ResponseEntity<ResponseModel> res = deleteRecord(deleteTableSQL);
            status = res.getStatusCode();
            result = "Successfully remove the book on category!";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }


    public ResponseEntity<ResponseModel> getAllAssignCategoryForBooks() {
        HttpStatus status;
        Object result;
        try {
            List<BookCategoryAssign> list = bookCategoryAssignRepo.findAll();
            if (list.isEmpty()) {
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

    public ResponseEntity<ResponseModel> deleteRecord(String deleteTableSQL){
        HttpStatus status;
        Object result;

        System.out.println(deleteTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            status = HttpStatus.CREATED;
            // Step 3: Execute the query or update query
            result = statement.execute(deleteTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }


}