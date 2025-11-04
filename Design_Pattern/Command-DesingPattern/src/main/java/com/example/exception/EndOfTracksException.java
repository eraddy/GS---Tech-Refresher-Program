package com.example.exception;

public class EndOfTracksException extends RuntimeException {
    public EndOfTracksException(String message) {
        super(message);
    }
}
