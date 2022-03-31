package com.book.management.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.management.demo.dao.entity.Books;

public interface BooksDao extends JpaRepository<Books, String>{

}
