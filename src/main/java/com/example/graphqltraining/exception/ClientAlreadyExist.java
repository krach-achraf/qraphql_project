package com.example.graphqltraining.exception;

public class ClientAlreadyExist extends AbstractGraphQLException{
    public ClientAlreadyExist(String message) {
        super(message);
    }
}
