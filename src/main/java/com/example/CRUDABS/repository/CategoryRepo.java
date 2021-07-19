package com.example.CRUDABS.repository;

import com.example.CRUDABS.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}