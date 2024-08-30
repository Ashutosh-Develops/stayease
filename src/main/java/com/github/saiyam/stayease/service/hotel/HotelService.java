package com.github.saiyam.stayease.service.hotel;

import com.github.saiyam.stayease.constant.HotelRoomStatus;
import com.github.saiyam.stayease.dto.hotel.AvailableHotelsDTO;
import com.github.saiyam.stayease.dto.hotel.request.HotelRequestBody;
import com.github.saiyam.stayease.dto.hotel.room.request.RoomRequestBody;
import com.github.saiyam.stayease.entity.Hotel;
import com.github.saiyam.stayease.entity.Room;
import com.github.saiyam.stayease.exception.HotelNotFoundException;
import com.github.saiyam.stayease.exception.InvalidInputException;
import com.github.saiyam.stayease.repository.IHotelRepository;
import com.github.saiyam.stayease.repository.IRoomRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class HotelService implements IHotelService{

    @Autowired
    private IHotelRepository iHotelRepository;
    @Autowired
    private IRoomRepository iRoomRepository;



    @Override
    public Hotel addHotel(HotelRequestBody hotelRequestBody) {

        HotelRequestBody validatedHotelRequestBody=validateHotelRequestBody(hotelRequestBody);
        String hotelName= validatedHotelRequestBody.getHotelName();
        String hotelDescription= validatedHotelRequestBody.getHotelDescription();
        String hotelLocation = validatedHotelRequestBody.getLocation();
        int numOfRooms = validatedHotelRequestBody.getNumberOfRooms();

        List<Room> rooms=new ArrayList<>();
        Hotel hotel=Hotel.createHotel(hotelName,hotelLocation,hotelDescription,rooms);
        Hotel savedHotel=iHotelRepository.save(hotel);


        return savedHotel;
    }

    @Override
    public Hotel deleteHotel(String hotelId) {
        return null;
    }

    @Override
    public Room addRoom(RoomRequestBody roomRequestBody) {

        RoomRequestBody validatedRoomRequestBody=validateRoom(roomRequestBody);

        Hotel hotel = iHotelRepository.findById(Long.parseLong(validatedRoomRequestBody.getHotelId())).get();

        HotelRoomStatus hotelRoomStatus = HotelRoomStatus.valueOf(validatedRoomRequestBody.getStatus());

        Room room = Room.createRoom(hotel,hotelRoomStatus);

        Room savedRoom=iRoomRepository.save(room);

        return savedRoom;
    }

    @Override
    public List<AvailableHotelsDTO> getAllAvailableHotels() {

        List<AvailableHotelsDTO> availableHotels=new ArrayList<>();
        List<Hotel> hotels= (List<Hotel>) iHotelRepository.findAll();

        // For each available hotel we need to get list of available rooms

        for(Hotel hotel:hotels){
            List<Room> rooms= hotel.getRooms();
            List<Room> availableRooms=new ArrayList<>();
            for(Room room:rooms){
                if(room.getHotelRoomStatus()==HotelRoomStatus.AVAILABLE){
                    availableRooms.add(room);
                }
            }
            AvailableHotelsDTO availableHotelsDTO=AvailableHotelsDTO.createAvailableHotelsDtoInstance(hotel.getId(),hotel.getHotelName(), hotel.getLocation(), hotel.getHotelDescription(),availableRooms);
            availableHotels.add(availableHotelsDTO);
        }
        return availableHotels;
    }

    private RoomRequestBody validateRoom(RoomRequestBody roomRequestBody){

        String hotelId=roomRequestBody.getHotelId();
        String statusR=roomRequestBody.getStatus();
        HotelRoomStatus hotelRoomStatus=HotelRoomStatus.AVAILABLE;

        if(hotelId==null||hotelId.isEmpty()){
            throw new InvalidInputException("Invalid hotel id "+hotelId);
        }

        Optional<Hotel> hotelOptional=iHotelRepository.findById(Long.parseLong(hotelId));

        if(hotelOptional.isEmpty()){
            throw new HotelNotFoundException("Hotel with id "+hotelId+" not found");
        }

        if(statusR!=null&&!statusR.isEmpty()){

            if(!statusR.equals(HotelRoomStatus.AVAILABLE.toString())){
                hotelRoomStatus=HotelRoomStatus.UNAVAILABLE;
            }
        }

       return new RoomRequestBody(hotelId,hotelRoomStatus.toString());
    }

    private List<Room> getRooms(int num,Hotel hotel){

        List<Room> rooms=new ArrayList<>();
        for(int i=1;i<=num;i++){
            rooms.add(Room.createRoom(hotel,HotelRoomStatus.AVAILABLE));
        }

        return rooms;
    }

    private HotelRequestBody validateHotelRequestBody(HotelRequestBody hotelRequestBody){

        String hotelName= hotelRequestBody.getHotelName();
        String hotelDescription = hotelRequestBody.getHotelDescription();
        int numOfRooms = hotelRequestBody.getNumberOfRooms();
        String location = hotelRequestBody.getLocation();


        if(hotelName==null || hotelName.isEmpty()){
            throw new InvalidInputException("Invalid hotelname"+hotelName);
        }

        if(hotelDescription==null || hotelDescription.isEmpty()){
           hotelDescription="Known for its high quality hospitability";
        }

        if(numOfRooms<0){
            numOfRooms=10;
        }

        if(location==null||location.isEmpty()){
            throw new InvalidInputException("Invalid location "+location);
        }

        return new HotelRequestBody(hotelName,location,hotelDescription,numOfRooms);
    }
}
