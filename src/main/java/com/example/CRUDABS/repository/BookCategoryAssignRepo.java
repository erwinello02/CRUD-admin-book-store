package com.example.CRUDABS.repository;

import com.example.CRUDABS.entity.BookCategoryAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryAssignRepo extends JpaRepository<BookCategoryAssign, Long> {
}
