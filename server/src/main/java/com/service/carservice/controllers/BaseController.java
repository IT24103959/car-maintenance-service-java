package com.service.carservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
public abstract class BaseController<T> {
    // Success response with body
    protected <R> ResponseEntity<R> response(R body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    // Success response with body, default status 200 OK
    protected <R> ResponseEntity<R> response(R body) {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Success response with array body (for list endpoints)
    protected <R> ResponseEntity<R[]> response(R[] body, Class<T> clazz) {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Success response with array body (for list endpoints)
    protected <R> ResponseEntity<R[]> response(com.service.carservice.util.LinkedList<R> list, Class<R> clazz) {
        return new ResponseEntity<>(list.modelDumpJson(clazz), HttpStatus.OK);
    }

    // Response with only status (no body)
    protected ResponseEntity<Void> response(HttpStatus status) {
        return new ResponseEntity<>(status);
    }
}
