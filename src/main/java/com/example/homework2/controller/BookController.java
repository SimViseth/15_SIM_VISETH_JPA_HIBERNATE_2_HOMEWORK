package com.example.homework2.controller;

import com.example.homework2.response.ApiResponse;
import com.example.homework2.response.BookRequest;
import com.example.homework2.entity.Book;
import com.example.homework2.repository.BookRepository;
import com.example.homework2.response.DeleteResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> insertBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Insert book successfully",
                        bookRepository.insertBook(bookRequest),
                        HttpStatus.CREATED,
                        LocalDateTime.now()
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAllBook() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Get all books successfully",
                        bookRepository.getAllBook(),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }

    @GetMapping("getBookById/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Get book by id successfully",
                        bookRepository.getBookById(id),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }

    @GetMapping("getBookByName/{name}")
    public ResponseEntity<ApiResponse<List<Book>>> getBookByTitle(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Get book by title successfully",
                        bookRepository.getBookByTitle(name),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable UUID id, @RequestBody BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Update book successfully",
                        bookRepository.updateBook(id, bookRequest),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteBook(@PathVariable("id") UUID id) {
        bookRepository.deleteBook(id);
        DeleteResponse response = new DeleteResponse(
                "Delete book successfully",
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
}
