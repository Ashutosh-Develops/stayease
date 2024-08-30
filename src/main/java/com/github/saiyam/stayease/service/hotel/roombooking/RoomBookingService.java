package com.github.saiyam.stayease.service.hotel.roombooking;

import com.github.saiyam.stayease.constant.HotelRoomStatus;
import com.github.saiyam.stayease.dto.hotel.room.request.RoomBookingRequestBody;
import com.github.saiyam.stayease.entity.Hotel;
import com.github.saiyam.stayease.entity.Room;
import com.github.saiyam.stayease.entity.RoomBooking;
import com.github.saiyam.stayease.entity.User;
import com.github.saiyam.stayease.exception.*;
import com.github.saiyam.stayease.repository.IHotelRepository;
import com.github.saiyam.stayease.repository.IRoomBookingRepository;
import com.github.saiyam.stayease.repository.IRoomRepository;
import com.github.saiyam.stayease.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomBookingService implements IRoomBookingService{

    @Autowired
    private IRoomBookingRepository iRoomBookingRepository;
    @Autowired
    private IHotelRepository iHotelRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IRoomRepository iRoomRepository;

    @Override
    @Transactional
    public RoomBooking bookRoom(String hotelId, RoomBookingRequestBody roomBookingRequestBody) {

        if(hotelId==null||hotelId.isEmpty()){
            throw new InvalidInputException("Invalid hotel id");
        }

        if(roomBookingRequestBody.getRoomId()==null||roomBookingRequestBody.getRoomId().isEmpty()){
            throw new InvalidInputException("Invalid room id");
        }

        Hotel hotel=iHotelRepository.findById(Long.parseLong(hotelId)).
                   orElseThrow(()->new HotelNotFoundException("Hotel with id "+hotelId+" not found"));

        long roomBookingId=Long.parseLong(roomBookingRequestBody.getRoomId());
        Room room=iRoomRepository.findById(roomBookingId).
                orElseThrow(()->new RoomNotFoundException("Room with id "+roomBookingId+" in hotel with hotel id "+hotelId+" does not exist"));

       //RoomBooking roomBooking=iRoomBookingRepository.findByRoom(roomBookingId);

       List<RoomBooking> bookings= (List<RoomBooking>) iRoomBookingRepository.findAll();

        // Room is booked already
       for(RoomBooking booking:bookings){
           if(booking.getRoom().getId()==room.getId()){
               throw new RoomAlreadyBookedException("Room with id "+roomBookingId+" is booked already");
           }
       }

        User user = getAuthenticatedUser();
        RoomBooking roomBookingInstance=RoomBooking.createRoomBookingInstance(user,room);
        // Set the status of room as unavilable
        room.setHotelRoomStatus(HotelRoomStatus.UNAVAILABLE);
        iRoomRepository.save(room);
        RoomBooking bookedRoom= iRoomBookingRepository.save(roomBookingInstance);

        return bookedRoom;
    }

    @Override
    @Transactional
    public RoomBooking cancelBooking(String bookingId) {

        if(bookingId==null||bookingId.isEmpty()){
            throw new InvalidInputException("Invalid booking id");
        }

        RoomBooking roomBooking=iRoomBookingRepository.findById(Long.parseLong(bookingId))
                .orElseThrow(()->new BookingNotFoundException("Booking with id "+bookingId+" does not exist"));

        Room room = roomBooking.getRoom();
        iRoomBookingRepository.delete(roomBooking);
        room.setHotelRoomStatus(HotelRoomStatus.AVAILABLE);
        iRoomRepository.save(room);

        return roomBooking;
    }

    private User getAuthenticatedUser(){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        String email=userDetails.getUsername();

        User user = iUserRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("User with email id "+email+" not found");
        }

        return user;
    }
}
