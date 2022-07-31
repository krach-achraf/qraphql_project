package com.example.graphqltraining.exception;

public class NotFoundException extends AbstractGraphQLException{
    public NotFoundException(String message) {
        super(message);
    }
}
