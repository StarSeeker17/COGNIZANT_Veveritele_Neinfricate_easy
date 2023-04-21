package ro.cognizant.coderun2023.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    public Book() {

    }

    public Book(Long id, String author, String bookName, String publisher) {
        this.id = id;
        this.author = author;
        this.bookName = bookName;
        this.publisher = publisher;
    }

    public Book(Long id, String author, String bookName) {
        this.id = id;
        this.author = author;
        this.bookName = bookName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String bookName;

    private String publisher;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
