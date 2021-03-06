package com.example.CRUDABS.entity;

import javax.persistence.*;

@Table(name="tbl_book_category_assign")
@Entity
public class BookCategoryAssign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "category_id")
    private long category_id;
    @Column(name = "book_id")
    private long book_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }
}