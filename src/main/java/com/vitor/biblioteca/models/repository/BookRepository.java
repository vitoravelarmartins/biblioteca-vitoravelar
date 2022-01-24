package com.vitor.biblioteca.models.repository;

import com.vitor.biblioteca.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookModel, Integer> {
    public List<BookModel> findByTitleIgnoreCase(String title);
    public List<BookModel> findByTitleContains(String title);
}
