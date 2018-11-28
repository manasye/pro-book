package com.blackmamba.model;

public class TransactionResponse {
    public boolean success;
    public String message;

    public TransactionResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
