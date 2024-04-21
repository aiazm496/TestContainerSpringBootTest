package com.akash.testcontainers.LearningTestContainers.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequestMapping("/books")
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> findById(@PathVariable Long id){
        return bookService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book create(@RequestBody Book book){
        return bookService.save(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) {
        return bookService.save(book);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/find/title/{title}")
    public List<Book> findByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("/find/data-after/{date}")
    public List<Book> findByPublishedDateAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-DD") LocalDate date){
        return bookService.findByPublishedDateAfter(date);
    }

}
