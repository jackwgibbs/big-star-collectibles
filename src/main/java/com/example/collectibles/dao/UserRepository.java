package com.example.collectibles.dao;

import com.example.collectibles.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
