package com.akash.testcontainers.LearningTestContainers.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByTitle(String title);

    @Query("select b from Book b where b.publishDate > :date")
    List<Book> findByPublishedDateAfter(LocalDate date);


}
