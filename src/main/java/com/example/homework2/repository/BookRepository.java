package com.example.homework2.repository;

import com.example.homework2.response.BookRequest;
import com.example.homework2.entity.Book;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Book insertBook(BookRequest bookRequest) {

        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setDescription(bookRequest.getDescription());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublicationYear(bookRequest.getPublicationYear());

        entityManager.persist(book);

        return book;
    }

    public List<Book> getAllBook() {
        return entityManager.createNativeQuery("SELECT * FROM book", Book.class).getResultList();
    }

    public Book getBookById(UUID id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> getBookByTitle(String name) {
        String sql = "SELECT * FROM book WHERE title ILIKE :name";
        Query query = entityManager.createNativeQuery(sql, Book.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Transactional
    public Book updateBook(UUID id, BookRequest bookRequest) {

        Book book = entityManager.find(Book.class, id);

        if (book != null) {
            book.setTitle(bookRequest.getTitle());
            book.setDescription(bookRequest.getDescription());
            book.setAuthor(bookRequest.getAuthor());
            book.setPublicationYear(bookRequest.getPublicationYear());
        }

        entityManager.detach(book);
        entityManager.merge(book);
        return book;
    }

    @Transactional
    public void deleteBook(UUID id) {
        Book book = entityManager.find(Book.class, id);

        entityManager.remove(book);

    }
}
