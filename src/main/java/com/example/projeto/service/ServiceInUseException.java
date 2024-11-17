package com.example.projeto.service;

public class ServiceInUseException extends Throwable {
    public ServiceInUseException(String s) {
        super(s);
    }
}
