package com.github.saiyam.stayease.repository;

import com.github.saiyam.stayease.entity.RoomBooking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomBookingRepository extends CrudRepository<RoomBooking,Long> {


}
