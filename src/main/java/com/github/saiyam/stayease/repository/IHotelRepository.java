package com.github.saiyam.stayease.repository;

import com.github.saiyam.stayease.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends CrudRepository<Hotel,Long> {
}
