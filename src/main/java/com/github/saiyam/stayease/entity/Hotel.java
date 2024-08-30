package com.github.saiyam.stayease.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String hotelName;

    private String location;
    private String hotelDescription;

    @OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY)
    private List<Room> rooms;

    public int getNumberOfAvailableRooms(){
        return rooms.size();
    }

    public static Hotel createHotel(String name,String location,String description,List<Room> rooms){

        Hotel hotel=new Hotel();
        hotel.setHotelName(name);
        hotel.setHotelDescription(description);
        hotel.setLocation(location);
        hotel.setRooms(rooms);

        return hotel;
    }

}
