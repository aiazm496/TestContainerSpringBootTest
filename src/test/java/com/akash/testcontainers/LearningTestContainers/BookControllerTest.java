package com.akash.testcontainers.LearningTestContainers;

import com.akash.testcontainers.LearningTestContainers.book.Book;
import com.akash.testcontainers.LearningTestContainers.book.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Testcontainers
@TestPropertySource(properties = "classpath:test-application.properties")
public class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    BookRepository bookRepository;

    private String BaseURI;

    @Autowired
    private TestRestTemplate restTemplate;

    // static, all tests share this mysql container
    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0-debian");


    @BeforeEach
    public void setUp(){
        BaseURI = "http://localhost:" + port;
        System.out.println("Test app server : " + BaseURI);

        Book book1 = new Book("harry", BigDecimal.valueOf(155.6), LocalDate.of(2000,1,1));
        bookRepository.save(book1);
    }

    @Test
    public void testFindAllBooks() throws InterruptedException {
        ParameterizedTypeReference<List<Book>> typeRef = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<Book>> response = restTemplate.exchange(
                BaseURI + "/books",
                HttpMethod.GET,
                null,
                typeRef
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().size());
        System.out.println(response.getBody());
    }

}
