package com.github.saiyam.stayease.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.saiyam.stayease.constant.HotelRoomStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private HotelRoomStatus hotelRoomStatus;
    private int guestCapacity=2;


    public static Room createRoom(Hotel hotel,HotelRoomStatus hotelRoomStatus){

        Room room=new Room();
        room.setHotelRoomStatus(hotelRoomStatus);
        room.setHotel(hotel);

        return room;
    }

}
