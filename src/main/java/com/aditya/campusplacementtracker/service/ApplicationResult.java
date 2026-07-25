package com.aditya.campusplacementtracker.service;

public class ApplicationResult {

    private boolean success;
    private String message;

    public ApplicationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}