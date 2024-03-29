package ru.sladkov.otus.spring.hw11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping({"/", "/books"})
    public String booksPage() {
        return "list";
    }

    @GetMapping("/book")
    public String bookDetailsPage() {
        return "details";
    }

    @GetMapping("/book/create")
    public String bookCreatePage() {
        return "create";
    }

    @GetMapping("/book/edit")
    public String bookEditPage() {
        return "edit";
    }
}
