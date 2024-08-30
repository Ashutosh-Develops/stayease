package com.github.saiyam.stayease.repository;

import com.github.saiyam.stayease.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User,Long> {

    User findByEmail(String email);
}
