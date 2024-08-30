package com.github.saiyam.stayease.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="room_id")
    private Room room;

    public static RoomBooking createRoomBookingInstance(User user,Room room){

        RoomBooking roomBooking=new RoomBooking();
        roomBooking.setUser(user);
        roomBooking.setRoom(room);

        return roomBooking;
    }
}
