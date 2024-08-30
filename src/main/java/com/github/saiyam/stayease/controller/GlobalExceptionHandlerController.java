package com.github.saiyam.stayease.controller;

import com.github.saiyam.stayease.dto.hotel.room.response.RoomBookingResponseBody;
import com.github.saiyam.stayease.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity invalidInputExceptionHandler(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BookingNotFoundException.class)
    public ResponseEntity bookingNotFoundExceptionHandler(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HotelNotFoundException.class)
    public ResponseEntity hotelNotFoundExceptionHandler(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RoomNotFoundException.class)
    public ResponseEntity roomNotFoundExceptionHandler(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity userNameNotFoundExceptionHandler(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RoomAlreadyBookedException.class)
    public ResponseEntity<RoomBookingResponseBody> roomAlreadyBookedExceptionHandler(){
        return new ResponseEntity<RoomBookingResponseBody>(new RoomBookingResponseBody(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity otherRunTimeExceptionsHandler(){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
