package com.example.CRUDABS.service;

import com.example.CRUDABS.entity.Book;
import com.example.CRUDABS.entity.Category;
import com.example.CRUDABS.models.ResponseModel;
import com.example.CRUDABS.repository.BookRepo;
import com.example.CRUDABS.repository.CategoryRepo;
import com.example.CRUDABS.responseUtils.ResponseFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ResponseFunctions responseFunctions;

    public ResponseEntity<ResponseModel> addCategory(Category category) {
        HttpStatus status;
        Object result;
        try {
            status = HttpStatus.CREATED;
            result = categoryRepo.save(category);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }

    public ResponseEntity<ResponseModel> addCategories(List<Category> category) {
        HttpStatus status;
        Object result;
        try {
            status = HttpStatus.CREATED;
            result = categoryRepo.saveAll(category);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }


    public ResponseEntity<ResponseModel> getAllCategories() {
        HttpStatus status;
        Object result;
        try {
            List<Category> list = categoryRepo.findAll();
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


    public ResponseEntity<ResponseModel> editCategory(Long id, Category category) {
        HttpStatus status;
        Object result;
        try {
            Optional<Category> cat = categoryRepo.findById(id);
            String category_name;
            if (category.getCategory_name() != null) {
                category_name = category.getCategory_name();
            } else {
                category_name = cat.get().getCategory_name();
            }
            cat.get().setCategory_name(category_name);
            status = HttpStatus.CREATED;
            result = categoryRepo.save(cat.get());
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }

    public ResponseEntity<ResponseModel> deleteCategory(Long id) {
        HttpStatus status;
        Object result;
        try {
            Optional<Category> category = categoryRepo.findById(id);
            if (category.isPresent()) {
                categoryRepo.delete(category.get());
            }
            status = HttpStatus.CREATED;
            result = category.get() + "Successfully Deleted!";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            result = e.getMessage();
        }
        return responseFunctions.response(status, result);
    }

}