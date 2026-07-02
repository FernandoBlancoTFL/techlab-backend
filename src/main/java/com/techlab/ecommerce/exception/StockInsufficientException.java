package com.techlab.ecommerce.exception;

public class StockInsufficientException extends RuntimeException {

    public StockInsufficientException(String message) {
        super(message);
    }

}
