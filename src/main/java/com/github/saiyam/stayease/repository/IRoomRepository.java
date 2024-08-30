package com.github.saiyam.stayease.repository;

import com.github.saiyam.stayease.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends CrudRepository<Room,Long> {
}
