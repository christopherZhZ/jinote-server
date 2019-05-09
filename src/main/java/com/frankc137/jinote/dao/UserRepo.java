package com.frankc137.jinote.dao;

import com.frankc137.jinote.dto.User;

import java.util.List;

public interface UserRepo extends Repo<User> {

    List<User> findByName(String name);

    User findUserByName(String name);

}
