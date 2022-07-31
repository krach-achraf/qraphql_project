package com.example.graphqltraining.exception;

public class ValidationException extends AbstractGraphQLException{
    public ValidationException(String message) {
        super(message);
    }
}
