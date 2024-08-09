package com.example.homework2.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime dateTime;
}
