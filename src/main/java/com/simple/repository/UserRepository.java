package com.simple.repository;

import com.simple.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRepository {

    public User getUserById(String id){
        return  new User(id,"name","I'm OK");
    }

}
