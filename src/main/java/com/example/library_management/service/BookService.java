package com.example.library_management.service;

import com.example.library_management.model.Book;
import com.example.library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() { return bookRepository.findAll(); }

    public void saveBook(Book book) { bookRepository.save(book); }

    public Book getBookById(Long id) { return bookRepository.findById(id).orElse(null); }

    public void deleteBook(Long id) { bookRepository.deleteById(id); }

    public List<Book> searchBooks(String title) { return bookRepository.findByTitleContaining(title); }
}
