package com.github.saiyam.stayease.service;

import com.github.saiyam.stayease.constant.HotelRoomStatus;
import com.github.saiyam.stayease.dto.hotel.AvailableHotelsDTO;
import com.github.saiyam.stayease.entity.Hotel;
import com.github.saiyam.stayease.entity.Room;
import com.github.saiyam.stayease.exception.RoomNotFoundException;
import com.github.saiyam.stayease.repository.IHotelRepository;
import com.github.saiyam.stayease.repository.IRoomRepository;
import com.github.saiyam.stayease.service.hotel.HotelService;
import com.github.saiyam.stayease.service.hotel.IHotelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class HotelServiceTest {


    @Test
    public void getAllAvailableHotels(){

        IHotelRepository iHotelRepository= Mockito.mock(IHotelRepository.class);
        IRoomRepository iRoomRepository=Mockito.mock(IRoomRepository.class);
        IHotelService iHotelService=new HotelService(iHotelRepository,iRoomRepository);
        List<Hotel> hotels=getListOfHotels();

        Mockito.when(iHotelRepository.findAll()).thenReturn(hotels);
        List<AvailableHotelsDTO> availableHotelsDTOList=iHotelService.getAllAvailableHotels();

        Assertions.assertNotNull(availableHotelsDTOList);
        // Test no of hotels
        Assertions.assertEquals(2,availableHotelsDTOList.size());

        Assertions.assertEquals("Hyatt",availableHotelsDTOList.get(0).getHotelName());
        Assertions.assertEquals(10,availableHotelsDTOList.get(0).getAvailableRooms().size());

        Assertions.assertEquals("ITC Maratha",availableHotelsDTOList.get(1).getHotelName());
        Assertions.assertEquals(15,availableHotelsDTOList.get(1).getAvailableRooms().size());

    }


    private List<Hotel> getListOfHotels(){

        Hotel hotelHyatt=Hotel.createHotel("Hyatt","Mumbai","World class hospitality",null);
        hotelHyatt.setId(1);
        List<Room> hotelHyattRooms=getRoomsForHotel(hotelHyatt,10);
        hotelHyatt.setRooms(hotelHyattRooms);


        Hotel hotelITC=Hotel.createHotel("ITC Maratha","Mumbai","Luxury meets comfort",null);
        hotelITC.setId(2);
        List<Room> hotelITCRooms=getRoomsForHotel(hotelITC,15);
        hotelITC.setRooms(hotelITCRooms);

        return List.of(hotelHyatt,hotelITC);
    }

    // Create 0-100 rooms
    private List<Room> getRoomsForHotel(Hotel hotel,int numOfRooms){

        if(numOfRooms<0){
            numOfRooms=0;
        }

        if(numOfRooms>100){
            numOfRooms=100;
        }

        List<Room> rooms=new ArrayList<>();

        for(int i=1;i<=numOfRooms;i++){
            rooms.add(Room.createRoom(hotel, HotelRoomStatus.AVAILABLE));
        }

        return rooms;
    }
}
