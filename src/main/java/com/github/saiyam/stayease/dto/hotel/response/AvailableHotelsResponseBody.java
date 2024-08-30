package com.github.saiyam.stayease.dto.hotel.response;

import com.github.saiyam.stayease.dto.hotel.AvailableHotelsDTO;
import com.github.saiyam.stayease.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableHotelsResponseBody {

    private long hotelId;
    private String hotelName;
    private String hotelLocation;
    private String hotelDescription;
    private List<RoomsAvailable> roomsAvailable;


    public static List<AvailableHotelsResponseBody> getResponseBodyFromDTO(List<AvailableHotelsDTO> availableHotelsDTOs){

        List<AvailableHotelsResponseBody> response=new ArrayList<>();

        for(AvailableHotelsDTO availableHotelsDTO:availableHotelsDTOs){
            List<Room> rooms= availableHotelsDTO.getAvailableRooms();
            List<RoomsAvailable> roomsAvailableList=new ArrayList<>();
            for(Room room:rooms){
                RoomsAvailable roomsAvailable=new RoomsAvailable(room.getId(),room.getGuestCapacity());
                roomsAvailableList.add(roomsAvailable);
            }
            AvailableHotelsResponseBody responseBody=new AvailableHotelsResponseBody(availableHotelsDTO.getHotelId(),
                   availableHotelsDTO.getHotelName(),
                    availableHotelsDTO.getHotelLocation(),
                    availableHotelsDTO.getHotelDescription(),
                    roomsAvailableList);

            response.add(responseBody);
        }
        return response;
    }
}
