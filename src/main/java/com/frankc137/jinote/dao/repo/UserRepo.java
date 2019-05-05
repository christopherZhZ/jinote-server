package com.frankc137.jinote.dao.repo;

import com.frankc137.jinote.dao.CrudRepo;
import com.frankc137.jinote.dto.User;

import java.util.List;

public interface UserRepo extends CrudRepo<User> {

    List<User> findByName(String name);

    default User findUserByName(String username) {
        List<User> users = findByName(username);
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

}
