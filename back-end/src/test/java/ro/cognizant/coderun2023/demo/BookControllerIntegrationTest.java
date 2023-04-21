package ro.cognizant.coderun2023.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book(1L, "Book1", "Author1");
        Book book2 = new Book(2L, "Book2", "Author2");
        bookRepository.save(book1);
        bookRepository.save(book2);



        List<Book> books = new BookController().getBooks();
        assertEquals(2, books.size());

        Book retrievedBook1 = books.get(0);
        assertEquals(book1.getBookName(), retrievedBook1.getBookName());
        assertEquals(book1.getAuthor(), retrievedBook1.getAuthor());

        Book retrievedBook2 = books.get(1);
        assertEquals(book2.getBookName(), retrievedBook2.getBookName());
        assertEquals(book2.getAuthor(), retrievedBook2.getAuthor());
    }

    @Test
    public void testCreateBook() {
        // Send a POST request to the /api/books endpoint with a new book object
        Book newBook = new Book(3L, "New Author", "New Book");
        ResponseEntity<Book> response = restTemplate.postForEntity("/api/books", newBook, Book.class);

        // Verify that the response contains the created book object, including a generated ID
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Book createdBook = response.getBody();
        assertNotNull(createdBook.getId());
        assertEquals("New Author", createdBook.getAuthor());
        assertEquals("New Book", createdBook.getBookName());

        // Verify that the book was inserted into the database
        Book persistedBook = bookRepository.findById(createdBook.getId()).orElse(null);
        assertNotNull(persistedBook);
        assertEquals("New Author", persistedBook.getAuthor());
        assertEquals("New Book", persistedBook.getBookName());
    }
}