package com.pattabhi.library.controller;

import com.pattabhi.library.entity.BookEntity;
import com.pattabhi.library.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/book/new")
    public String showCreateForm(Model model) {
        BookEntity book = new BookEntity();
        model.addAttribute("book", book);
        return "create_book";
    }

    @PostMapping("/books")
    public String saveBook(BookEntity book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @PostMapping("/book/delete")
    public String deleteBook(BookEntity book) {
        bookRepository.delete(book);
        return "redirect:/";
    }
}










