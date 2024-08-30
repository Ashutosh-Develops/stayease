package com.github.saiyam.stayease.exception;

public class RoomAlreadyBookedException extends  RuntimeException{
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}
